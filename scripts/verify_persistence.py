import os
import hashlib
import json
import datetime

SNAPSHOT_FILE = "persistence_snapshot.json"
LOG_FILE = "persistence_error.log"

def calculate_hash(filepath):
    """Calculate SHA256 hash of a file."""
    hasher = hashlib.sha256()
    try:
        with open(filepath, "rb") as f:
            while chunk := f.read(8192):
                hasher.update(chunk)
        return hasher.hexdigest()
    except (IOError, OSError):
        return None

def create_snapshot(directory):
    """Create a snapshot of file hashes in the directory."""
    snapshot = {}
    for root, _, files in os.walk(directory):
        if ".git" in root or "node_modules" in root or ".gemini" in root:
            continue
        for file in files:
            path = os.path.join(root, file)
            snapshot[path] = calculate_hash(path)
    
    with open(SNAPSHOT_FILE, "w") as f:
        json.dump(snapshot, f, indent=4)
    print(f"Snapshot created successfully at {datetime.datetime.now()}")

def verify_integrity(directory):
    """Compare current file hashes with the snapshot."""
    if not os.path.exists(SNAPSHOT_FILE):
        print("No snapshot found. Run with --snapshot first.")
        return

    with open(SNAPSHOT_FILE, "r") as f:
        snapshot = json.load(f)

    errors = []
    current_time = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")

    for path, old_hash in snapshot.items():
        if not os.path.exists(path):
            errors.append(f"[{current_time}] MISSING: {path}")
            continue
        
        new_hash = calculate_hash(path)
        if new_hash != old_hash:
            errors.append(f"[{current_time}] MODIFIED: {path}")

    if errors:
        with open(LOG_FILE, "a") as f:
            f.write("\n".join(errors) + "\n")
        print(f"Errors detected! Check {LOG_FILE} for details.")
    else:
        print("Integrity check passed. No changes detected since last lock.")

if __name__ == "__main__":
    import sys
    project_dir = os.path.abspath(os.path.join(os.path.dirname(__file__), ".."))
    if "--snapshot" in sys.argv:
        create_snapshot(project_dir)
    elif "--verify" in sys.argv:
        verify_integrity(project_dir)
    else:
        print("Usage: python verify_persistence.py [--snapshot | --verify]")
