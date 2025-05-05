# Neo Take-Home Assignment

REST-API for managing characters in a proof-of-concept for a new game.

## How to Run the Application

### Option 1: Using Java and Maven Wrapper

#### Prerequisites

1. **Java 17**

2. **Maven Wrapper**  
   The Maven Wrapper (`mvnw`) is included in this project, so there's no need to install Maven separately.

#### Steps to Run

1. Open a terminal in the root directory of the project.
2. Run the following command to start the application:
    - **On Unix/Linux/Mac**:
      ```bash
      ./mvnw spring-boot:run
      ```
    - **On Windows**:
      ```cmd
      mvnw.cmd spring-boot:run
      ```

3. Access the application in your browser at:
   ```
   http://localhost:8080
   ```

---
### Option 2: Using Docker

#### Prerequisites

1. **Docker**  

#### Steps to Run

1. Navigate to the root directory of the project (where the `docker-compose.yml` file is located).
2. Run the following command to start the application:
   ```bash
   docker-compose up app
   ```

3. Access the application in your browser at:
   ```
   http://localhost:8080
   ```

---
## Running Tests

This project includes automated tests to ensure the application's functionality. Below are the instructions to run the tests.


### Option 1: Running Tests with Maven Wrapper

#### Prerequisites

1. **Java 17**

2. **Maven Wrapper**
   The Maven Wrapper (`mvnw`) is included in the project, so you don't need Maven installed separately.

#### Steps to Run Tests

1. Open a terminal in the root directory of the project.
2. Run the following command to execute the tests:
    - **On Unix/Linux/Mac**:
      ```bash
      ./mvnw test
      ```
    - **On Windows**:
      ```cmd
      mvnw.cmd test
      ```

The test results will be displayed in the terminal.

---

### Option 2: Running Tests with Docker

If you prefer to run tests in a Docker container, follow these steps.

#### Prerequisites

1. **Docker**


#### Steps to Run Tests

1. Build the Docker image if it has not been built yet:
   ```bash
   docker build -t <project-name> .
   ```

2. Run the tests inside a Docker container:
   ```bash
   docker run <project-name> ./mvnw test
   ```

OR
1. Build the Docker image if it has not been built yet:
   ```bash
   docker-compose up tests
   ```

---

## API Reference

The complete API reference can be found at `http://localhost:8080/swagger-ui/index.html`

### 1. Create Character

#### POST `/characters`

##### Body

```json
{
  "name": "Elandor",
  "job": "thief"
}
```

### 2. Get All Characters

#### GET `/characters`

### 3. Get One Character

#### GET `/characters/{id}`
| Parameter | Type   | Description                  |
|:----------|:-------|:-----------------------------|
| `id`      | `uuid` | ID of the character to fetch |

### 4. Battle

#### POST `/battle`

##### Body

```json
{
  "player1": "8edf636b-c429-4aeb-a3af-2a0c39475978",
  "player2": "0b64c1c3-2dfb-4856-9bce-9b1bbcaeadf3"
}
```
