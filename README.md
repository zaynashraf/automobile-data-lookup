# Automobile Data Lookup System
## Overview
This Java-based GUI application allows users to search and filter automobile data from the Auto MPG Dataset based on miles per gallon (mpg) and horsepower values. It reads from a file and stores that data into a MySQL table, where the automobile data is stored and managed. The program provides a user-friendly interface with sliders and input fields to refine search queries and retrieve relevant automobile records.

## Features
GUI Interface: Built using Swing, featuring sliders for mpg and horsepower selection.

Database Connectivity: Connects to a MySQL database to retrieve and store automobile data.

Dynamic Search Functionality: Allows users to search for specific car entries based on mpg and horsepower.

Data Import: Reads automobile data from a file and inserts it into the database.

## Setup Instructions
### Prerequisites
Install Java (JDK 8 or later)

Install MySQL

Install MySQL Connector and place it in your project's file path

Setup should look like this:

<img width="924" alt="Screenshot 2025-04-02 at 8 14 30 PM" src="https://github.com/user-attachments/assets/28d38f8b-58a0-421f-a9cc-b7ad577388c3" />

### Database Setup
Log into MySQL server on terminal:

```
mysql -u root -p
```

Create the database:

```sql
CREATE DATABASE Auto;
```

Switch to the database:

```sql
USE Auto;
```

Create the mpg table:

```sql
CREATE TABLE mpg (
    mpg VARCHAR(10),
    cylinder VARCHAR(10,
    displacement VARCHAR(10),
    horsepower VARCHAR(10),
    weight VARCHAR(10),
    acceleration VARCHAR(10),
    year VARCHAR(10),
    origin VARCHAR(10),
    name VARCHAR(255)
);
```
Modify the MySQL connection settings in Main.java:

```java
Connection connection = DriverManager.getConnection(
    "jdbc:mysql://localhost/Auto", "root", "password");
```

Change "root" and "password" to your MySQL username and password

## How to Run
Change current directory

```
cd /file/path
```

Change "file" and "path" to the file path which contains the folder which stores your Java file

Compile and Run:

```
javac -cp ".:mysql-connector-j-version.jar" Main.java
java -cp ".:mysql-connector-j-version.jar" Main.java
```

Change "version" to your MySQL connector version number

### Interact with the GUI:

Use the sliders to set mpg and horsepower values.

In the text box, type "find" to retrieve specific records or type "all" to retrieve the entire dataset

Click Find to retrieve matching records from the database.

<img width="416" alt="Screenshot 2025-04-02 at 8 42 21 PM" src="https://github.com/user-attachments/assets/5527cf02-9b64-43b1-8a6e-1d703e68f0cd" />

