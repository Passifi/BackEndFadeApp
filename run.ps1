$env:DB_URL="jdbc:postgresql://localhost:5432/fadeapp"
$env:DB_USERNAME="fadeapp"
$env:DB_PASSWORD="local-dev-password"

.\mvnw spring-boot:run
