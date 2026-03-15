$orders = @(
    @{
        customerId = 15
        customerName = "Gunjan Kumari"
        cafeId = 1
        cafeName = "indian"
        totalAmount = 300.0
        orderType = "Dine-In (Book a Table)"
        tableNumber = "5"
        paymentMethod = "GPay"
        items = @("Coffee (x2)", "Sandwich (x1)")
        status = "PENDING"
    },
    @{
        customerId = 15
        customerName = "Gunjan Kumari"
        cafeId = 1
        cafeName = "indian"
        totalAmount = 150.0
        orderType = "Takeaway"
        paymentMethod = "PhonePe"
        items = @("Cappuccino (x1)")
        status = "PREPARING"
    }
)

foreach ($order in $orders) {
    $json = $order | ConvertTo-Json
    Invoke-RestMethod -Uri "http://localhost:8080/api/orders/place" -Method Post -Body $json -ContentType "application/json"
}
