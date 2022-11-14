# Run Configurations

Both Gradle and IntelliJ run configurations are available.

Those are fine-grained configurations executing tests on desired level (Micro, Unit, Component, Api, Contract, Gui, System)

## Gradle

# TODOOOOOOOOOO 
See gradle tasks by executing `./gradlew `

To run all tests at once use task `test`

## IntelliJ Idea


# Tests

## `Pricing` and `Vendor` Microservices

- All internal tests are under `src/test/java`
- Contracts are under `src/contractTest/resources`
- Generated contract tests are in `build/generated-test-sources/contractTest`

## `vendor-ui`

Protractor tests are located under `src/e2e/test`

## `SharedLib` and `TicketsTests`

- All tests are under `src/test/java`

# Vendor API Stubs

To start Vendor Stub, based on Contract tests, see [README](services/Vendor/api-stubs/README.MD)

# Project Structure

Important paths in project are:

```
Tickets
└── infra/kafka/docker-compose.yml      // Use to start Apache Kafka in docker on your machine
└── libs/SharedLib                      // Java library embedded in services
└── services                            // Microservices building up Tickets system
│   └── Pricing
│   └── Vendor
│   └── vendor-ui
└── SystemTests/TicketsTests            // separate project holding E2E System Tests for Tickets
```

# Reference Documentation

For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.5/gradle-plugin/reference/html/)
* [Testcontainers](https://www.testcontainers.org/)
* [Sleuth](https://docs.spring.io/spring-cloud-sleuth/docs/current/reference/htmlsingle/spring-cloud-sleuth.html)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.7.5/reference/htmlsingle/#actuator)
* [Okta Spring Boot documentation](https://github.com/okta/okta-spring-boot#readme)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.7.5/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.5/reference/htmlsingle/#web)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Custom Login Page Example](https://github.com/okta/samples-java-spring/tree/master/custom-login)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
