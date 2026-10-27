# Jenkins Spring Boot Pipeline Practice

A beginner-friendly Java 17 / Spring Boot / Maven project for practising Jenkins pipelines.

## Endpoints

- `GET http://localhost:8081/` — health-style welcome message
- `GET http://localhost:8081/api/greet?name=Sanchit` — greeting endpoint

## Requirements

- JDK 17
- Maven 3.6.3 or newer
- Git
- Jenkins with shell-step support and access to Java/Maven

## Run locally in Eclipse or terminal

Import the folder into Eclipse as an **Existing Maven Project**.

Or from a terminal in the project folder:

```bash
mvn clean test
mvn spring-boot:run
```

Open `http://localhost:8081/` or `http://localhost:8081/api/greet?name=Sanchit`.

To build a JAR:

```bash
mvn clean package
```

The JAR is created in `target/`.

## Push to a public GitHub repository

Create a public repository named `jenkins-springboot-practice` on GitHub. Do not initialize it with a README or other files. Then, from this project folder:

```bash
git init
git add .
git commit -m "Add Spring Boot Jenkins pipeline practice project"
git branch -M main
git remote add origin https://github.com/YOUR_GITHUB_USERNAME/jenkins-springboot-practice.git
git push -u origin main
```

Replace `YOUR_GITHUB_USERNAME` with your GitHub username.

## Configure Jenkins

1. In Jenkins, select **New Item**.
2. Name it `jenkins-springboot-practice`.
3. Select **Pipeline** and click **OK**.
4. In the Pipeline section, set **Definition** to `Pipeline script from SCM`.
5. Set **SCM** to `Git`.
6. Paste the public GitHub repository URL.
7. Set **Branch Specifier** to `*/main`.
8. Set **Script Path** to `Jenkinsfile`.
9. Save and click **Build Now**.
10. Open **Console Output** and check the stages. Jenkins should also show JUnit test results.

## Important Jenkins prerequisite

The Jenkins agent must have JDK 17 and Maven installed and available on its PATH. The `Jenkinsfile` uses `sh`, `java`, and `mvn`. If Jenkins runs as a macOS service, it may have a different PATH from your interactive Terminal. If it runs in Docker, Java and Maven must be available inside the agent/container.
