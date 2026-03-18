# PowerShell Integrity Checker
param(
    [switch]$Snapshot,
    [switch]$Verify
)

$SnapshotFile = Join-Path $PSScriptRoot "persistence_snapshot.json"
$LogFile = Join-Path $PSScriptRoot "persistence_error.log"
$ProjectDir = (Get-Item $PSScriptRoot).Parent.FullName

function Get-FileHash-SHA256($Path) {
    try {
        return (Get-FileHash -Path $Path -Algorithm SHA256).Hash
    } catch {
        return $null
    }
}

if ($Snapshot) {
    Write-Host "Creating snapshot of $ProjectDir..." -ForegroundColor Cyan
    $SnapshotData = @{}
    $Files = Get-ChildItem -Path $ProjectDir -Recurse -File | Where-Object { 
        $PSItem.FullName -notmatch "\\.git\\" -and 
        $PSItem.FullName -notmatch "\\node_modules\\" -and
        $PSItem.FullName -notmatch "\\.gemini\\"
    }

    foreach ($File in $Files) {
        $Hash = Get-FileHash-SHA256 $File.FullName
        if ($Hash) {
            $SnapshotData[$File.FullName] = $Hash
        }
    }

    $SnapshotData | ConvertTo-Json | Out-File -FilePath $SnapshotFile -Encoding utf8
    Write-Host "Snapshot created successfully at $(Get-Date)." -ForegroundColor Green
}

if ($Verify) {
    Write-Host "Verifying integrity..." -ForegroundColor Cyan
    if (-not (Test-Path $SnapshotFile)) {
        Write-Host "No snapshot found. Run with -Snapshot first." -ForegroundColor Red
        return
    }

    $SnapshotData = Get-Content $SnapshotFile | ConvertFrom-Json
    $Errors = @()
    $CurrentTime = Get-Date -Format "yyyy-MM-dd HH:mm:ss"

    foreach ($Item in $SnapshotData.PSObject.Properties) {
        $Path = $Item.Name
        $OldHash = $Item.Value

        if (-not (Test-Path $Path)) {
            $Errors += "[$CurrentTime] MISSING: $Path"
            continue
        }

        $NewHash = Get-FileHash-SHA256 $Path
        if ($NewHash -ne $OldHash) {
            $Errors += "[$CurrentTime] MODIFIED: $Path"
        }
    }

    if ($Errors.Count -gt 0) {
        $Errors | Out-File -FilePath $LogFile -Append -Encoding utf8
        Write-Host "Errors detected! Check $LogFile for details." -ForegroundColor Red
    } else {
        Write-Host "Integrity check passed. No changes detected since last lock." -ForegroundColor Green
    }
}
