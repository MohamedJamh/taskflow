# Spring boot & Spring Security

Welcome to this repository! This project is a comprehensive Spring Boot and spring security using jwt as a starter kit designed to accelerate your development process.

## Standalone Mode

### 1. Clone the Repository

```bash
git clone [Your Repository Link]
cd project-name
git fetch origin
git switch authentication
```
### 2. Run PostgreSQL & pgAdmin Using Docker Compose

```bash
docker-compose -f docker-compose-standalone.yml up -d
```

### 3. Set Up PostgreSQL Database

- Open pgAdmin at `http://localhost:5050`
- Login using the provided credentials.
- Create a new database named taskflow.

### 4. Run the Spring Boot Application
Make sure Maven is installed. Then run the following command:

```bash
mvn clean install
mvn spring-boot:run
```

### 5. Run SonarQube Analysis (Optional)

Modify `src/main/resources/sonar-project.properties` with your SonarQube credentials.
Run SonarQube analysis:

```bash
mvn clean verify sonar:sonar
```
Access the SonarQube dashboard at `http://localhost:9000`.

## Dockerized Mode

### 1. Download Docker Compose File

Download the Docker Compose file from 
[Docker Compose Link](https://www.dropbox.com/scl/fi/7y6mx4g2cqkqrpnbwtyhd/docker-compose.yaml?rlkey=9pa24lvee1ze271jeobqv9r7l&dl=0).

- 💀 Please note that changing the credentials of the database service in the Compose file will impact the startup of the Spring Boot Docker image.
- Note: If the spring_auth container fails to start, restart it. :smile:


## Finally

Copy the Postman collection URL: [Postman Collection Link](https://www.dropbox.com/scl/fi/c5s3gy0s5dmplii6e9xke/taskflowAuth.json?rlkey=i9n3jns84dgh4pg8zgtzujla0&dl=0)
- Open Postman.
- Click on "Import" on the left-hand side.
- 📝 Note : after importing the collection modifiy the `baseUrl` environment variable to `localhost:8081/api/v1`

Feel free to explore, fork, and adapt this project to suit your specific requirements. If you encounter any issues or have suggestions, please open an issue or contribute.
🌟🌟🌟
Happy coding!



