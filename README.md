# ☕ Bookafé - Artisan Coffee Platform

Welcome to **Bookafé**, a premium digital ecosystem designed to bridge the gap between artisan cafes and coffee lovers. From high-quality visuals to real-time order tracking, Bookafé provides a seamless experience for customers, staff, and administrators alike.

---

## 🗺️ Project Ecosystem at a Glance

Bookafé is more than just a website; it’s a full management suite.

1.  **Customers** 🥐: Browse, Register (KYC), and Order.
2.  **Admin** 📋: Verify Users (KYC), Manage Cafes, and Monitor Stats.
3.  **Cafe Owners** 🏠: Setup Cafes, Manage Staff (Chefs/Waiters), and Control Menus.
4.  **Chefs** 🍳: Real-time Kitchen Queue, Prep Management, and Stock Control.
5.  **Waiters** 🛎️: Floor Plan Map, Ready Service Pickup, and Final Delivery.

---

## 🚀 Part 1: Technical Setup (For Developers)

Follow these steps to get the engine running.

### 1. Prerequisites
- **Java 17+** | **Node.js (v18+)** | **MySQL Server**

### 2. Backend Setup (`/coffee`)
1.  **Navigate**: `cd coffee`
2.  **Database**: Open `src/main/resources/application.properties`. Update your MySQL URL, username, and password.
3.  **Run**: `mvn spring-boot:run`
    - *Api hosted at: `http://localhost:8080`*

### 3. Frontend Setup (`/frontend/coffee-platform`)
1.  **Navigate**: `cd frontend/coffee-platform`
2.  **Install**: `npm install`
3.  **Run**: `npm run dev`
    - *Site hosted at: `http://localhost:5173`*

---

## 👥 Part 2: Role-Based User Guide (Step-by-Step)

### 1. 📋 Platform Administrator
The Admin is the "Gatekeeper" of the platform.
- **KYC Approval Portal**: Go to `/admin-portal` -> `Admin Approval`. Review the uploaded Government IDs (Aadhar/PAN).
- **Approval Flow**: Once you click `Approve`, the system automatically generates a temporary password and emails it to the user.
- **Analytics**: Monitor total visitors, active cafes, and user growth from the main dashboard.

### 2. 🥐 The Customer
- **Step 1: Multi-Step Signup**: Register at `/signup-customer`. You must provide personal info, address, and academic/work history.
- **Step 2: KYC Upload**: Upload a clear photo of your Government ID.
- **Step 3: Waiting Room**: You will be redirected to `/waiting` until the Admin approves your account.
- **Step 4: Activation**: Check your email for the temporary password. Log in and set your permanent password at `/set-password`.
- **Step 5: Ordering**: Browse the menu, select your items, choose a table number (or takeaway), and place your order.

### 3. 🏠 Cafe Owner
- **Cafe Registration**: Setup your cafe profile at `/signup-cafe` with FSSAI and GST details.
- **Staff Management**: From your **Owner Profile**, you can add **Chefs** and **Waiters**. They will receive their own login credentials.
- **Menu Control**: Add items to your menu and set prices.

### 4. 🍳 The Chef (Kitchen Central)
- **Live Queue**: Open the `/chef` dashboard to see "Pending" orders.
- **Cooking Flow**: 
    1. Click `Start Preparing` to move the order to "Preparing" status.
    2. Click `Mark as Ready` once the food is done. This notifies the Waiters.
- **Stock Control**: Use the `Menu Control` tab to mark items as "Out of Stock" or "In Stock" in real-time if ingredients run out.

### 5. 🛎️ The Waiter (Hospitality)
- **Floor Plan**: Use the `Floor Plan` tab to see which tables are **Occupied** 🟤 or **Available** ⚪.
- **Service Ready**: When the Chef marks an order as "Ready", it appears in your `Service Ready` tab.
- **Delivery Flow**: 
    1. Click `Pick Up Order` when you take it from the kitchen.
    2. Click `Mark as Served` once you deliver it to the table.

---

## 🔄 The Order Life-Cycle

To understand how the project "handles" data, follow this status chain:
`PENDING` (New Order) ➡️ `PREPARING` (Chef is cooking) ➡️ `READY` (Waiting for Waiter) ➡️ `DELIVERED` (With Waiter) ➡️ `SERVED` (Finished).

---

## 🛠️ Troubleshooting & Handling

| Common Issue | Solution |
| :--- | :--- |
| **User can't log in** | Check if the Admin has approved them in the `/admin` portal. |
| **Orders not showing** | Ensure the Chef/Waiter is assigned to the correct Cafe in their profile. |
| **Email not arriving** | Check `application.properties` for correct SMTP (Gmail) settings. |
| **Database missing** | Ensure you created `coffee_db` in MySQL before running the backend. |

---

© 2026 **Bookafé**. *Crafting the perfect coffee experience, one byte at a time.*
