# 🛒 E-Commerce Management System

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/JavaFX-Application-blue?style=for-the-badge" alt="JavaFX">
  <img src="https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven">
  <img src="https://img.shields.io/badge/Status-In%20Development-orange?style=for-the-badge" alt="Status">
</p>

<p align="center">
  A desktop application for managing users, products, and customer orders.
  <br>
  Developed as a university Object-Oriented Programming project.
</p>

---

## 📖 About the Project

**E-Commerce Management System** is a Java desktop application designed to demonstrate the fundamentals of object-oriented programming and application development.

The application provides a graphical user interface built with JavaFX. It allows users to manage customer information, maintain a product catalog, and organize orders through a simple menu-based interface.

The project focuses on practicing object-oriented design, inheritance, encapsulation, collections, and event-driven programming.

## ✨ Features

### 👥 User Management
- Add new users.
- View users in a table.
- Delete users.
- Store user information, including name, email, password, and role.
- Distinguish between customers and employees.

### 📦 Product Management
- Add products to the catalog.
- View product information.
- Delete products.
- Store product names, prices, and stock quantities.
- Support different product types, including books, electronics, and clothing.

### 🧾 Order Management
- View existing orders.
- Associate orders with customers.
- Track products included in each order.
- Calculate order totals.
- Display order information.

### 🛍️ New Order
- Select a customer.
- Choose products for an order.
- Create an order using the available product information.

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Application logic and object-oriented programming |
| JavaFX | Graphical user interface |
| FXML | Defining interface layouts |
| Scene Builder | Designing JavaFX interfaces |
| Maven | Dependency and project management |
| IntelliJ IDEA | Development environment |
| Git and GitHub | Version control |

## 🏗️ Object-Oriented Design

The project uses object-oriented programming principles to organize its classes and responsibilities.

- **Encapsulation:** Fields are kept private and accessed through getters and setters.
- **Inheritance:** Specialized classes extend common base classes.
- **Polymorphism:** Different product types can share a common interface or superclass.
- **Collections:** `ArrayList` is used to store and manage application data.
- **Event-driven programming:** JavaFX controls respond to user interactions.

### Main Classes

| Class | Responsibility |
|---|---|
| `User` | Stores common user information |
| `Customer` | Represents a customer |
| `Employee` | Represents an employee |
| `Product` | Defines common product properties |
| `Book` | Represents a book product |
| `Electronic` | Represents an electronic product |
| `Clothing` | Represents a clothing product |
| `Order` | Stores customer, product, and total order information |

## 📂 Project Structure

The project follows a Maven-based Java application structure. The exact package and file organization may vary as development continues.

```text
Console Application/
├── pom.xml
├── .mvn
├── .idea
└── src/
    └── main/
        ├── java/
        │   └── com/study
        │       ├── User.java
        │       ├── Customer.java
        │       ├── Employee.java
        │       ├── Product.java
        │       ├── Book.java
        │       ├── Electronic.java
        │       ├── Clothing.java
        │       └── Order.java
JavaFX Application/
```

## 🚀 Getting Started

### Prerequisites

Make sure you have installed:

- JDK 25.
- IntelliJ IDEA.
- Maven.

### Run the Application

1. Clone or download this repository.
2. Open the project in IntelliJ IDEA.
3. Allow Maven to load the dependencies specified in `pom.xml`.
4. Make sure the JavaFX configuration is correct.
5. Run the application's main class.

If the project is configured with the Maven JavaFX plugin, you may be able to launch it from the terminal using:

```bash
mvn clean javafx:run
```

The command depends on the configuration in `pom.xml`.

## 🎨 User Interface

The application uses JavaFX to provide a graphical interface for navigating between user management, product management, and order management.

The interface uses a navy and orange color scheme to maintain a consistent visual style.

*Screenshots i will add later.*

## 🎯 Learning Objectives

This project was created to practice:

- Applying object-oriented programming principles in Java.
- Designing class hierarchies with inheritance.
- Building desktop applications with JavaFX.
- Connecting FXML layouts with controller classes.
- Handling user input and button events.
- Displaying data with JavaFX `TableView`.
- Managing relationships between users, products, and orders.
- Organizing a multi-class Maven project.


## 👨‍💻 Author

**Ilya Kubyshenko**

University project focused on Java, JavaFX, and Object-Oriented Programming.

---

<p align="center">
  <b>Built with Java ☕ and a passion for learning. 🚀</b>
</p>
