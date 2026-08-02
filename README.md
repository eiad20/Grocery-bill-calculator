# 🛒 Grocery Bill Calculator

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) 

A robust, console-based grocery store simulation built in Java. This project serves as a practical demonstration of foundational backend architecture, focusing on Object-Oriented Programming (OOP) principles, precise financial calculations, and custom exception handling.

## ✨ Features
*   **Interactive CLI:** A user-friendly console interface for browsing items, building a shopping list, and processing checkouts.
*   **Robust Calculations:** Uses `java.math.BigDecimal` for accurate monetary calculations, avoiding common floating-point precision errors.
*   **Custom Error Handling:** Implements bespoke exceptions to gracefully manage user input errors without crashing the application.
*   **Itemized Receipts:** Generates a detailed receipt showing purchased items, the total item count, and the final bill.

## 🧠 Architecture & Java Principles Applied
This codebase was architected to showcase clean software patterns and core Java features:
*   **Single Responsibility Principle (SRP):** Clear separation of concerns where `Store` manages the catalog, `Order` tracks the cart state, and `GroceryBillCalculator` handles the UI loop.
*   **Encapsulation:** Item properties (`name` and `price`) are marked as `private final` and accessed strictly through getter methods, ensuring data immutability after instantiation.
*   **Custom Exceptions:** `ItemNotFoundException` inherits from `Exception` to provide a strict contract for handling catalog search failures safely.
*   **Collection Framework:** Utilizes `List<Item>` and `ArrayList` for dynamic memory management, replacing rigid static arrays.
*   **Precision Math:** Explicitly uses `BigDecimal.ZERO` and `.add()` methods to maintain financial accuracy over standard primitive data types.

## 📂 Project Structure
```text
📦 Grocery
 ┣ 📜 GroceryBillCalculator.java  # Application entry point and interactive UI loop
 ┣ 📜 Store.java                  # Manager class for the catalog and search logic
 ┣ 📜 Order.java                  # Represents a customer cart and calculates totals
 ┣ 📜 Item.java                   # Data model encapsulating product details
 ┗ 📜 ItemNotFoundException.java  # Custom exception for invalid item requests
```
