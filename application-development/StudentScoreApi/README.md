# 📘 Student Score API

A **Spring Boot REST API** for managing students and their subject scores.
It allows creating students, storing their scores, and generating statistical reports for their performance (mean, median, mode).

---

## 🚀 Features

* **Create** a new student with multiple subject scores.
* **Retrieve** a paginated report of all students.
* Automatically compute performance metrics for each student's scores:
    * **Mean** (Average)
    * **Median** (Middle value)
    * **Mode** (Most frequent value)
* **Data validation** for score inputs.
* **JPA** + **H2 (in-memory)** database for persistence.
* **Unit tests** with JUnit 5 and Mockito.

---

## 🧱 Tech Stack

| Technology            | Version/Framework | Purpose                         |
|:----------------------|:------------------|:--------------------------------|
| **Java**              | 21                | Core programming language       |
| **Spring Boot**       | 3                 | Backend framework               |
| **Spring Data JPA**   | -                 | Database interaction            |
| **Postgresql**        | -                 | development database            |
| **Gradle**            | -                 | Build and dependency management |
| **JUnit 5 / Mockito** | -                 | Testing                         |

---

## ⚙️ Setup Instructions

### 1️⃣ Clone the repository

```bash
git clone [https://github.com/okeyximo/software-engineer-test.git](https://github.com/okeyximo/software-engineer-test.git)
cd application-development/StudentScoreApi
````

### 2️⃣ Build the project

```bash
./gradlew clean build
```

### 3️⃣ Run the application

```bash
./gradlew bootRun
```

The application will start on:
👉 **`http://localhost:8080`**

-----

## 🧩 API Endpoints

### ➕ Create Student

**`POST /api/students`**

**Request Body Example:**

```json
{
  "firstName": "John",
  "lastName": "Doe",
  "scores": {
    "Math": 90,
    "English": 85,
    "Science": 70,
    "History": 60,
    "Geography": 75
  }
}
```

**Response Example:**

```json
{
  "studentId": 1,
  "firstName": "John",
  "lastName": "Doe",
  "scores": {
    "Math": 90,
    "English": 85,
    "Science": 70,
    "History": 60,
    "Geography": 75
  },
  "mean": 76.0,
  "median": 75.0,
  "mode": null
}
```

### 📄 Get Student Reports (Paginated)

**`GET /api/students/report?page=0&size=10`**

Returns a paginated list of `StudentReportDTO` objects, which include the student's details and calculated statistics.

-----

## 🧪 Testing

Run all tests:

```bash
./gradlew test
```

Includes:

* **Unit Tests:** `StudentServiceTest`
* **Utility Tests:** `StatisticsUtilTest` (for mean, median, mode calculations)

-----

## 🧠 Project Structure

The key components of the API are organized as follows:

```
src/
├─ main/java/com/example/StudentScoreApi/
│   ├─ controller/        → REST Controllers (Handle HTTP requests)
│   ├─ dto/               → Data Transfer Objects (Request/Response models)
│   ├─ entities/          → JPA Entities (Database structure)
│   ├─ repository/        → Spring Data Repositories (Database access)
│   ├─ service/           → Business Logic Layer (Core functionality/calculations)
│   └─ util/              → Utility classes (e.g., StatisticsUtil for computations)
└─ test/java/com/example/StudentScoreApi/
  └─ service/           → Unit Tests for business logic
```

-----

## 📊 Example Calculation

For the set of scores: **`[90, 85, 70, 60, 75]`**

* **Mean:** $\frac{90 + 85 + 70 + 60 + 75}{5} = 76.0$
* **Median:** $75.0$ (The middle value after sorting: $60, 70, \mathbf{75}, 85, 90$)
* **Mode:** $None$ (No score appears more than once)

-----

## 👨‍💻 Author

**Okechukwu**
Senior Java Developer 💻
📧 `[okey.onwuchekwa@gmail.com]`
