# Library Management System

A Java Swing-based desktop application for managing a library's book inventory and member records. This project provides a user-friendly interface for both administrators and students to interact with the library system.

## Features

*   **Dual User Modules:** Separate interfaces and functionalities for Administrators and Students.
*   **User Authentication:** Secure login and registration for both admins and students.
*   **Administrator Privileges:**
    *   Add, update, and view book details.
    *   Issue books to students and manage return records.
    *   View all registered students.
    *   Place orders for new books.
*   **Student Privileges:**
    *   View available books.
    *   Check their own issued book records.
*   **Database Connectivity:** Connects to a database to persist book and user data.

## Technologies Used

*   **Language:** Java
*   **UI Framework:** Java Swing
*   **IDE:** Apache NetBeans
*   **Build Tool:** Ant

## Getting Started

Follow these instructions to get a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

*   [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)
*   [Apache NetBeans IDE](https://netbeans.apache.org/download/index.html)
*   A running SQL database instance (e.g., MySQL).

### Installation

1.  **Clone the repository:**
    ```sh
    git clone https://github.com/your-username/LibraryManagementSystem.git
    ```
2.  **Open the project in NetBeans:**
    *   Launch NetBeans IDE.
    *   Go to `File -> Open Project...`
    *   Navigate to the cloned project directory and select it.
3.  **Configure the Database:**
    *   Create a new database for the project.
    *   Open the `src/Project/Connector.java` file.
    *   Update the database connection details (URL, username, password) to match your local database configuration.
    *   You may need to create the necessary tables in your database.

## How to Run

1.  Make sure you have completed the installation and database setup steps.
2.  In the NetBeans IDE, right-click on the project in the "Projects" pane.
3.  Select "Run" from the context menu, or press the `F6` key.
4.  The application's main window should now appear.

## License

This project is licensed under the MIT License - see the [LICENSE.txt](LICENSE.txt) file for details.
