# Apparel Disposal, Donation, and Recycling Platform

## Project Overview

This is a web-based platform where users can submit their unused or worn-out apparel for proper disposal, donation, or recycling. The platform collects user data, including information about the apparel type, its condition, and the user's location, along with an image of the apparel.

The project uses **Java (Servlets)** for the backend, **MySQL** as the database, and **HTML, CSS, and JavaScript** for the frontend. Users can input data via a form, which is then saved into a MySQL database. The form includes uploading images, which are stored on the server.

## Features Implemented

- **User Form Submission**: Users can submit information about the apparel, including:
  - Username
  - Email
  - Apparel Type (e.g., Shirt, Pants, etc.)
  - Apparel Condition (e.g., New, Used, Damaged)
  - Location (to enable donation or disposal options)
  - Image Upload

- **Data Persistence**: The submitted data is saved into a MySQL database, including the file path for uploaded images.

- **File Handling**: Uploaded images are stored on the server and their paths are saved in the database.

- **Dynamic Backend**: Java servlets are used to handle form submissions and interact with the MySQL database.

## Technology Stack

- **Backend**: Java (Servlets)
- **Database**: MySQL
- **Frontend**: HTML, CSS, JavaScript
- **Web Server**: Apache Tomcat
- **Database Connector**: MySQL JDBC Driver

## Installation and Setup

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Apache Tomcat (or any other Java web server)
- MySQL Database
- MySQL JDBC Driver
- IDE such as Eclipse for development (optional but recommended)

### Steps to Install and Run the Project

#### 1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-repo/apparel-platform.git
   ```

#### 2. **Set Up MySQL Database**
   - Install MySQL and start the MySQL server.
   - Run the following SQL script to create the database and table:

   ```sql
   CREATE DATABASE apparel_db;

   USE apparel_db;

   CREATE TABLE apparel (
       id INT PRIMARY KEY AUTO_INCREMENT,
       username VARCHAR(100),
       email VARCHAR(100),
       apparel_type VARCHAR(100),
       apparel_condition VARCHAR(100),  
       location VARCHAR(255),
       image_path VARCHAR(255),
       submission_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
   );
   ```

#### 3. **Add MySQL Connector/J to the Project**
   - Download the MySQL JDBC Driver from [MySQL's official site](https://dev.mysql.com/downloads/connector/j/).
   - If using Maven, add the following dependency to your `pom.xml`:
     ```xml
     <dependency>
         <groupId>mysql</groupId>
         <artifactId>mysql-connector-java</artifactId>
         <version>8.0.30</version>
     </dependency>
     ```

   - If not using Maven, add the JAR file to your classpath or build path.

#### 4. **Set Up the Project in Eclipse**
   - Open Eclipse.
   - Create a new **Dynamic Web Project** and import the cloned project files.
   - Configure **Apache Tomcat** as the server in Eclipse.
   - Make sure you add the `mysql-connector-java.jar` to the project's build path if using the JAR file.

#### 5. **Configure Database Connection**
   - Open the `ApparelServlet.java` file.
   - Update the database connection parameters:
     ```java
     String jdbcUrl = "jdbc:mysql://localhost:3306/apparel_db?useSSL=false&serverTimezone=UTC";
     String dbUser = "yourUsername"; // Replace with your MySQL username
     String dbPass = "yourPassword"; // Replace with your MySQL password
     ```

#### 6. **Run the Project**
   - Start the Apache Tomcat server from Eclipse.
   - Visit `http://localhost:8080/apparel-platform` to access the form.

#### 7. **Upload Image Directory Configuration**
   - Images uploaded through the form are saved in a directory on the server.
   - Make sure the `UPLOAD_DIR` in `ApparelServlet.java` points to a valid directory on your system. Example:
     ```java
     private static final String UPLOAD_DIR = "uploads";
     ```
   - Create the folder if necessary (e.g., under `WebContent` or another accessible location).

#### 8. **Test Submissions**
   - Fill out the form and submit apparel data.
   - The data should be stored in the MySQL database, and the images should be saved in the specified upload directory.

## Project Structure

- **WebContent/**: Contains HTML, CSS, and JavaScript files.
  - `index.html`: The main form for submitting apparel details.
  
- **src/**: Contains Java Servlet code.
  - `ApparelServlet.java`: The servlet handling form submissions, connecting to the database, and saving the image file paths.

- **lib/**: Contains external libraries (e.g., `mysql-connector-java.jar`).

## Future Enhancements

- **User Authentication**: Add user accounts with login and registration features.
- **Apparel Categorization**: Allow users to categorize apparel by size, material, or brand.
- **Admin Panel**: Add an admin interface to view and manage submissions.
- **API Integration**: Enable integration with third-party donation and recycling services.
  
## Conclusion

This project demonstrates a simple web-based platform for collecting apparel data for recycling, donation, or disposal. It includes a form for users to submit details, a backend system for handling submissions, and a MySQL database for storing information.

---

Feel free to modify this `README.md` according to any specific features or configurations in your project.
