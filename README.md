# Automobile Data Lookup System
## Overview
This Java-based GUI application allows users to search and filter automobile data based on miles per gallon (mpg) and horsepower values. It utilizes a MySQL database, where the automobile data is stored and managed. The program provides a user-friendly interface with sliders and input fields to refine search queries and retrieve relevant automobile records.

## Features
GUI Interface: Built using Swing, featuring sliders for mpg and horsepower selection.

Database Connectivity: Connects to a MySQL database to retrieve and store automobile data.

Dynamic Search Functionality: Allows users to search for specific car entries based on mpg and horsepower.

Data Import: Reads automobile data from a file and inserts it into the database.

## Setup Instructions
Prerequisites
Install Java (JDK 8 or later)

Install MySQL

Install MySQL Connector and place it in your project's file path

## Database Setup
Create the database:

sql
Copy
Edit
CREATE DATABASE Auto;'''
Switch to the database:

sql
Copy
Edit
USE Auto;
Create the mpg table:

sql
Copy
Edit
CREATE TABLE mpg (
    id INT AUTO_INCREMENT PRIMARY KEY,
    mpg VARCHAR(10),
    cylinder INT,
    displacement VARCHAR(10),
    horsepower VARCHAR(10),
    weight VARCHAR(10),
    acceleration VARCHAR(10),
    year VARCHAR(10),
    origin VARCHAR(10),
    name VARCHAR(255)
);
Update database credentials in the Java code:

Modify the MySQL connection settings in Main.java:

java
Copy
Edit
Connection connection = DriverManager.getConnection(
    "jdbc:mysql://localhost/Auto", "root", "your_password");
Running the Application
Compile and Run:

sh
Copy
Edit
javac Main.java
java Main
Interact with the GUI:

Use the sliders or input fields to set MPG and horsepower values.

Click Find to retrieve matching records from the database.
