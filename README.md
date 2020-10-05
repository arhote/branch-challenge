## Build Instructions

### Running inline
You can simply run the application with the following `./mvnw spring-boot:run`

### Building then running via java
You can build the jar with `./mvnw clean package`, then you can run the jar with the
command `java -jar target/core-0.0.1-SNAPSHOT.jar`

## Usage

Navigate to `http://localhost:8080/` to access the service. You will see the default info
for the "octocat" user displayed for you. To search for a user simply append a `user`
parameter to the url like so: `http://localhost:8080/?user=<desired username>`