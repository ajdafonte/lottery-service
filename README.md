# lottery-service

Simple component which represents a service that handles a lottery system.

This component exposes a REST HTTP API.

## Getting started

### Clone Repository

Use Git or checkout with SVN using the following web URL:
```
git clone https://github.com/ajdafonte/lottery-service.git
```

### Build and run component

Execute the following gradle task:
```
./gradlew build
```

### Running the tests

Execute the following gradle task:
```
./gradlew test
```

### Running the component
```
./gradlew bootRun
```

### Import into IDE

This is a Gradle project (build.gradle). Here are some instructions on how to import a Gradle project in some of the most popular IDEs:
- IntelliJ - https://www.jetbrains.com/help/idea/gradle.html#gradle_import
- Eclipse - https://www.vogella.com/tutorials/EclipseGradle/article.html#import-an-existing-gradle-project

## Technical Decisions

In this section will be described some details about technical decisions made.

### Stack

- Java 11
- Spring Boot 2.4
- Gradle 6.8.3

### Dependencies

- Annotations: Lombok, Javax Inject
- Testing libs: JUnit 5, Mockito, Hamcrest, RestAssured

### Storage

All records handled by this component are stored in memory with the following structures:
 - `ConcurrentHashMap<String, LotteryEntity> LOTTERY_STORAGE` - Stores all the lotteries 
   - Key: String that represents the id of the Lottery
 - `ConcurrentSkipListMap<LocalDateTime, LotteryPurchaseEventEntity> LOTTERY_PURCHASE_EVENTS_STORAGE` - Stores all the lotteries purchase events
   - Key: LocalDateTime that indicates when the purchase event has occurred

### Available endpoints

- `POST /v1/lotteries` - Allows to create a new Lottery.

  - Request body: 
    - name - Name of the Lottery
    - date - Date of the Lottery
    - numberTickets - Number of Tickets that will be generated for this Lottery
  
  - Response:
    - 201 CREATED with an object representing the new Lottery
  
- `GET /v1/lotteries` - Retrieves all the Lotteries.
  
  - Query parameter:
    - date - Allow filtering by date of Lottery

  - Response:
    - 200 OK with an object containing all the Lotteries
  
- `POST /v1/lotteries/{id}/purchase` - Submits a new purchase for a specific Lottery
  
  - Path variable:
    - id - Id of the Lottery

  - Request body:
    - userId - Id of the User
    - tickets - Tickets to purchase

  - Response:
    - 204 NO CONTENT

A [Postman collection](lottery-service.postman_collection.json) is available with an example for each endpoint. 
    
### General remarks & assumptions

- Hexagonal architecture was the architectural pattern used to structure this component
- Development was done following TDD and Clean Code best practices  
- All tests that ends with the suffix 'ITTest' are an integration or acceptance tests
- To calculate the winning ticket of a Lottery, a scheduled task is performed by this component (each day at midnight)
- When a user purchase tickets for a specific Lottery
  - Only records the purchase (like an event), does not have any logic on the user side (ex: does not check 
    if the user has already purchased those tickets or not)
- Component will run on port 8080
- The following aspects were not considered during the development of this component and could be considered in future next stages:
  - Definition of a Ticket domain object (currently, there are no constraints or logic for a Lottery ticket)
  - Improve handling and definition of service Exceptions
  - Add a config properties to specify a different scheduling setup to calculate the winning ticket of a Lottery
  - Improve mechanism that calculates the winning ticket of a Lottery (ex: perform the calculation when the service
    consumes an event and publish an event with the result)
  - OpenAPI specification
  - API authentication/authorization
  - Definition of admin and user operations (ie. only an admin can create a Lottery)
  - Pagination support  
  - Add a persistence storage (ie. DB)
  - Add Docker support