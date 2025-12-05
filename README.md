# 🎮 Game Store Backend API

A robust RESTful API built with **Spring Boot** for a digital video game store. This application allows users to browse games, manage a shopping cart, purchase games (move to library), and request refunds. It features role-based security (Admin vs. Client) using **Spring Security**.

---

## 🚀 Technologies Used

* **Java 21**
* **Spring Boot 3.x** (Web, Data JPA, Security)
* **PostgreSQL** (Database)
* **Maven** (Build Tool)
* **Lombok** (Boilerplate reduction)

---

## ⚙️ How to Run

1.  Open the project in **IntelliJ IDEA**.
2.  Locate the main class: `src/main/java/com/gamestore/backend/BackendApplication.java`.
3.  Click the **Green Play Button** (Run).
4.  The application will start on `http://localhost:8080`.

---

## 🔐 Security & Authentication

The API uses **HTTP Basic Authentication**.

* **How to test:** In Postman, go to the **Authorization** tab, select **Basic Auth**, and enter the username/password.
* **Roles:**
    * **CLIENT:** Can browse, buy, and refund.
    * **ADMIN:** Can manage the store inventory (Add/Update/Delete games).

---

## 📡 API Endpoints Reference

### 👤 User Management

| Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :--- |
| `POST` | `/users/register` | Register a new account | ❌ No Auth |
| `GET` | `/users/me` | See current user profile | ✅ Basic Auth |
| `GET` | `/users` | List all users (Admin view) | ✅ Basic Auth |

### 🎮 Game Catalog

| Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :--- |
| `GET` | `/games` | List all games | ❌ No Auth |
| `POST` | `/games` | Add new game | ✅ **ADMIN** |
| `PUT` | `/games/{id}` | Update game details | ✅ **ADMIN** |
| `DELETE` | `/games/{id}` | Delete game | ✅ **ADMIN** |

### 🛒 Shopping System

| Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :--- |
| `POST` | `/shop/cart/{gameId}` | Add game to cart | ✅ **CLIENT** |
| `POST` | `/shop/checkout` | Buy games in cart | ✅ **CLIENT** |
| `POST` | `/shop/refund/{gameId}` | Refund a game | ✅ **CLIENT** |

---

## 🧪 Step-by-Step Testing Guide (Postman)

Copy these JSON bodies to test the full flow of the application.

### Step 1: Create an Admin Account
* **URL:** `POST http://localhost:8080/users/register`
* **Auth:** No Auth
```json
{
  "username": "admin",
  "password": "000",
  "role": "ADMIN"
}
```
### Step 2: Create a Client Account
* **URL:** `POST http://localhost:8080/users/register`
* **Auth:** No Auth
```json
{
  "username": "player1",
  "password": "123",
  "role": "CLIENT"
}

```
### Step 3: Populate Store (As Admin)
* **URL:** `POST http://localhost:8080/games`
* **Auth:** No Auth
```json
{
  "title": "GTA VI",
  "price": 70.0,
  "description": "The most awaited game."
}

```
### Step 4: Client Buys a Game
* **URL:** `POST http://localhost:8080/shop/cart/1`
* **Auth:** Basic Auth (Username: player1, Password: 123)
* **Response:** "Game added to cart!"
  
* * **URL:** `POST http://localhost:8080/shop/cart/1`
* **Auth:** Basic Auth (Username: player1, Password: 123)
* **Response:** "Checkout successful!"

### Step 5: Verify Purchase (Check Library)
* **URL:** `GET http://localhost:8080/users/me`
* **Auth:** Basic Auth (Username: player1, Password: 123)
* **Check:** "The library array in the JSON response should contain the game.
