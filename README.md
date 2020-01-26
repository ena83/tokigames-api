# tokigames-api
Tokigames API coding test

### Prerequisites

* Java Development Kit (JDK), version 11 or higher.

* Maven

### Packaging structures

Packaging structure of this project is based domain/business function followed by package structure following Hexagonal Architecture

* `com.tokigames.api.flights` (Business function/domain).
* `com.tokigames.api.flights.adapter` (Adapter layer for which application need to communicate with, it translate whatever comes from delivery mechanism into method call in the `application`).
* `com.tokigames.api.flights.application` (Application layer to collaborate services and domain objects).
* `com.tokigames.api.flights.domain` (Business domain logic should be reside here).
* `com.tokigames.api.flights.port` (Port layer (interfaces) that need to be implemented by adapters).

  
### Command to start the project

**1. How to run the service?**

**mvn spring-boot:run**

> Route(Method - GET) : http://localhost:8080/flights?city=Singapore&sortBy=ARRIVAL_CITY&page=1&size=10

Query Parameters
* city (Departure from or Arrival to at this city)
* sortBy (DEPARTURE_CITY,ARRIVAL_CITY)
* page (sublist of return list, base on size)
* size (size of return list, by default is 20)

**2. How to run the test-cases?**

**mvn test**

