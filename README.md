
# Simple CRUD Application in Play Framework (Scala)

This repository contains a simple CRUD application built using the Play Framework with Scala. The application allows users to perform Create, Read, Update, and Delete operations on a sample dataset.

---

## Prerequisites

Before running the project, ensure you have the following installed:

1. **Java Development Kit (JDK):** Version 8 or higher.
2. **Scala:** Version 2.13.x or compatible with the Play Framework version used.
3. **SBT (Scala Build Tool):** Version 1.5.x or higher.
4. **Database:** (Optional) Configure your preferred database (e.g., PostgreSQL, MySQL).

---

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/play-scala-crud.git
cd play-scala-crud
```

### 2. Build the Project

Run the following command to build the project and download all required dependencies:

```bash
sbt compile
```

### 3. Run the Application

Start the application in development mode:

```bash
sbt run
```

The application will start and be available at `http://localhost:9000`.

---

## Configuration

### Database Configuration

To configure the database, update the `application.conf` file in the `conf` directory. Example configuration for a PostgreSQL database:

```hocon
db.default.driver = org.postgresql.Driver
db.default.url = "jdbc:postgresql://localhost:5432/your_database"
db.default.username = "your_username"
db.default.password = "your_password"
```

Ensure you have the correct database driver dependency in your `build.sbt` file:

```scala
libraryDependencies += "org.postgresql" % "postgresql" % "42.6.0"
```

### Running in Production

To run the application in production mode:

1. Build the application:
   ```bash
   sbt dist
   ```
2. Extract the generated zip file in the `target/universal` directory.
3. Run the extracted binary:
   ```bash
   ./your-app-name/bin/your-app-name -Dplay.http.secret.key="your-secret-key"
   ```

---

## Features

- **Create:** Add new entries to the database.
- **Read:** View existing entries.
- **Update:** Modify existing entries.
- **Delete:** Remove entries.

---

## API Endpoints

| Method | Endpoint       | Description              |
|--------|----------------|--------------------------|
| POST   | `/create`      | Create a new record.     |
| GET    | `/list`        | Retrieve all records.    |
| PUT    | `/update/:id`  | Update a specific record.|
| DELETE | `/delete/:id`  | Delete a specific record.|

---

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

---

## Contributions

Contributions are welcome! Feel free to submit issues or pull requests.

