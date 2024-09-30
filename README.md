**Stock Management System**
A Stock Management System developed using Spring Boot for managing products, categories, suppliers, and stock transactions. This system provides CRUD operations (Create, Read, Update, Delete) for managing various entities and APIs for external integrations.

**Features**
**Manage Products:** Add, update, delete, and list products with details like name, description, price, quantity, and category.
**Manage Categories:** Add, update, delete, and list product categories.
**Manage Suppliers:** Add, update, delete, and list suppliers with details like contact information.
**Track Stock Transactions:** Log transactions for stock movements (IN/OUT).
**Technologies Used**
Java (Spring Boot)
Hibernate (JPA)
PostgreSQL (or any relational database)
Maven (for dependency management)
Lombok (for cleaner code)
Project Structure
**The project follows the MVC (Model-View-Controller) architecture:**
**Entities:** Represent the database tables using Java classes.
**DTOs:** Data Transfer Objects used for interaction between the client and server.
**Repositories:** Handle database operations using JPA.
**Controllers:** Define RESTful APIs for interacting with the system.
**Installation & Setup**
Prerequisites
Java Development Kit (JDK) 21
PostgreSQL (or any other database)
IDE (like IntelliJ IDEA, Eclipse)
