# 🧪 REST Assured API Testing: Java Examples & Patterns

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![REST Assured](https://img.shields.io/badge/REST_Assured-5A9E3D?style=for-the-badge)
![TestNG](https://img.shields.io/badge/TestNG-FF6C37?style=for-the-badge)
![GraphQL](https://img.shields.io/badge/GraphQL-E10098?style=for-the-badge&logo=graphql&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/GitHub_Actions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white)

A hands-on collection of **API test automation patterns** in **Java** with **REST Assured** and **TestNG**. It covers CRUD workflows, authentication (OAuth 2.0), GraphQL, file uploads, POJO serialization and deserialization, request/response specifications, and data-driven testing from Excel, JSON and TestNG data providers. A **GitHub Actions** workflow builds the project on every push.

For the BDD framework version of these ideas, see [RESTAssuredFramework](https://github.com/nancyabcec1058/RESTAssuredFramework).

---

## ✨ Topics covered

| Topic | Class | What it shows |
|---|---|---|
| **End-to-end CRUD** | `AddDeletePlaceAPI` | POST → PUT → GET → DELETE chain, passing `place_id` between calls and checking the updated address |
| **Payload from file** | `AddDeletePlaceAPI_JsonDataFile` | Reads the request body from an external JSON file |
| **Response parsing** | `jsonParsing`, `JsonParsing_sum` | Navigates nested JSON with `JsonPath` and checks that item totals match the purchase amount |
| **Serialization** | `Serialization` | Builds the request body from Java POJOs instead of raw JSON strings |
| **Spec builders** | `Serialization_specBuilder` | Reusable `RequestSpecBuilder` and `ResponseSpecBuilder` for base URI, params and expected status/content type |
| **Deserialization** | `OAuthCoursesUsingPOJO` | Maps a nested JSON response into POJOs (`GetCourse` → `Courses` → `api`/`web`/`mobile` lists) and asserts course titles |
| **OAuth 2.0** | `OAuthCourses`, `OAuth2` | Client-credentials token flow and the Google authorization-code flow |
| **E-commerce API flow** | `EcommerceAPI` | Login (token) → add product with **multipart image upload** → create order → delete product |
| **GraphQL** | `GraphQL` | Queries and mutations with variables; creates a location and checks returned character, location and episode names |
| **Jira REST API** | `JiraBug` | Creates an issue, attaches a file, reads it back and deletes it |
| **Data-driven: DataProvider** | `LibraryAPI_DataParameterization_HashMap` | Adds and deletes books for several ISBN/aisle sets |
| **Data-driven: Excel** | `LibraryAPI_DataParameterization_Excel` + `Files/ExcelUtility` | Reads book data from `Library.xlsx` with Apache POI, calls the API, writes the returned ID back to Excel |
| **Data-driven: JSON/HashMap** | `LibraryAPI_DataParameterization_Json` | Sends a `HashMap` body that REST Assured serializes to JSON |
| **Basic CRUD** | `LibraryAPI` | Add and delete book tests with Hamcrest body assertions |

## 🧰 Tech stack

| Tool | Version | Purpose |
|---|---|---|
| Java | 23 | Language |
| REST Assured | 6.0.1 | HTTP requests and fluent assertions |
| TestNG | 7.12.0 | Test runner and data providers |
| Hamcrest | 3.0 | Matchers for response assertions |
| Jackson Databind | 2.22.1 | POJO ↔ JSON mapping |
| Apache POI | 5.5.0 | Reading and writing Excel test data |
| GitHub Actions | | CI build on push |

## 📁 Project structure

```
RestAssuredAPI/
├── .github/workflows/rest-assured-test.yml   # CI: JDK 23 + mvn clean test
├── Library.xlsx                              # Excel test data
├── pom.xml
└── src/
    ├── Files/
    │   ├── payload.java                      # Reusable JSON payloads
    │   └── ExcelUtility.java                 # Apache POI read/write helper
    ├── POJO/                                 # Request + response models
    │   ├── AddPlace.java, Location.java
    │   ├── GetCourse.java, Courses.java, apiCourse.java, webAutomationCourse.java, mobileCourse.java
    │   └── EcommerceLogin.java, EcommerceLoginResponse.java, EcoomerceaddOrder.java, OrderDetails.java
    └── *.java                                # One class per topic (see table above)
```

## 🚀 Getting started

**Prerequisites:** JDK 23 (or update `<release>` in `pom.xml`) and Maven 3.x.

```bash
git clone https://github.com/nancyabcec1058/RestAssuredAPI.git
cd RestAssuredAPI
mvn clean test
```

Run a single TestNG class from the IDE, or with:

```bash
mvn test -Dtest=LibraryAPI
```

Classes without `@Test` (for example `GraphQL`, `EcommerceAPI`, `OAuthCoursesUsingPOJO`) have a `main` method and can be run directly from the IDE.

> **Local setup note:** a few examples upload files or read payloads from absolute Windows paths, and the Jira and Google OAuth examples need your own credentials. Update these before running.

## 🔄 Continuous integration

`.github/workflows/rest-assured-test.yml` runs on every push to `main`, and can also be started by hand (`workflow_dispatch`). It sets up Temurin JDK 23 and runs `mvn clean test`.

## 👩‍💻 Author

**Nancy**, SDET / Senior QA Automation Engineer
[GitHub](https://github.com/nancyabcec1058) · [LinkedIn](https://www.linkedin.com/in/nancy1912)

> Most examples use public practice APIs built for automation learning.
