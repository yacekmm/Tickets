# About `Tickets`

## Business goal

Tickets system is a cutting-edge Concert tickets selling platform with a revolutionary pricing algorithm and super friendly for Concert organizers (called Vendors) to sell their concerts with a remarkable profit.

## Purpose

The system is built as a training example for [Testing Microservices](https://bottega.com.pl/szkolenie-ms-testing).

## Architecture

The overall System consists of many microservices. This repository holds a subset of them, essential for Vendors to create concerts. High level architecture is below.

![img.png](docs/TicketsArchitecture.png)

# Importing to IDE

You should import the `Tickets/build.gradle` as a root project to your IDE.

## Publish Spring Cloud Contracts

Some tests expect External services stubs managed by Spring Cloud Contract so those might fail until contracts are published to local maven repository because of AetherStubDownloader cannot download stubs. To publish contracts execute: `./gradlew publishToMavenLocal` in `Tickets/` or run IntelliJ configs named `Vendor/Pricing contract publish`


## Verify after import

Execute tests to check if the system is working on your machine and tests are green.

I suggest running IntelliJ task `Tickets test --all` to run all kinds of tests in the system. Alternatively you can run `./gradlew test` for all the services. Follow this doc to find out details.

Reach me out in case of any difficulties.

# Running

Both Gradle and IntelliJ run configurations are available.

Those are fine-grained configurations starting apps and executing tests on a desired level (Micro, Unit, Component,
Dependency, Api, Contract, GUI, System)

## Starting off the whole Tickets system

Tickets System consists of several services you need to run to see it working. See running instructions below:

| Service    | IntelliJ Idea task | Manual command                                                          |
|------------|--------------------|-------------------------------------------------------------------------|
| Kafka      | `kafka run`        | run `docker-compose.yml` using `docker compose up` under `infra/kafka/` |
| Vendor GUI | `vendor-ui run`    | `ng serve` from `vendor-ui/`                                            |
| Vendor     | `Vendor run`       | `./gradlew bootRun` from `Vendor/`                                      |
| Pricing    | `Pricing run`      | `./gradlew bootRun` from `Pricing/`                                     |

## Opening the GUI

Open `http://localhost:4200` in your browser and enjoy!

# Run and test configurations

## Gradle

Execute gradle tasks by executing `./gradlew <task-name>`, where task names are defined
in `services/<service>/gradle/test-suites.gradle`. To run all tests at once use task `test`

Tasks available for Tests are as follows

### Running `Vendor`, `Pricing` and `SharedLib`

- `bootRun`: starts the service
- `microTest`: runs suites suffixed with `microTest` and `microSpec`
- `unitTest`: runs suites suffixed with `unitTest` and `unitSpec`
- `compTest`: runs suites suffixed with `compTest` and `compSpec`
- `depTest`: runs suites suffixed with `depTest` and `depSpec`
- `apiTest`: runs suites suffixed with `apiTest` and `apiSpec`
- `contractTest`: runs contract tests defined in `/src/contractTests/resources/contracts`

Note: `SharedLib`, as an utils library, contains only `microTest`s,

#### Prerequisites

Installed:

- gradle
- JDK 17+
- IDE (recommended: IntelliJ Idea)
- git
- docker
- docker compose

Operating System: Ubuntu (recommended). `Tickets` is also ready to run on Windows, however docker on Windows is unpredictable.

### Running `vendor-ui`

As an Angular project the task config is different here.

- `ng serve`: runs app on under `localhost:4200`
- `protractor conf.js` executes isolated GUI tests on `vendor-ui`. Note: execute from `services/vendor-ui/src/e2e`

#### Prerequisites

Installed:

- node.js `sudo apt install nodejs`
- Angular `npm install -g @angular/cli`
- Protractor `npm install -g protractor`
- Chrome Browser

Before start, install node dependencies by running `npm install` from `services/vendor-ui`

### Running `TicketsTests`

The project holds System Tests only, all under `src/test` with gradle task:

- `test`: runs tests against the Tickets System

## Run configs in IntelliJ Idea

All the configurations listed above are also exported to this repo as an IntelliJ Idea configurations. They reside
in `.run` directory and should be automatically imported once `Tickets` project is imported.

Some run configurations are using [Multirun plugin](https://plugins.jetbrains.com/plugin/7248-multirun) to start all microservices in the system. Consider installing the plugin: 

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
????????? infra/kafka/docker-compose.yml              // Use to start Apache Kafka in docker on your machine
????????? libs/SharedLib                              // Java library embedded in services
????????? services                                    // Microservices building up Tickets system
???   ????????? Pricing
???   ????????? Vendor
|       ????????? api-stubs/run-contract-stubs.sh     // execute to run Vendor API Stubs based on Contract Tests to test vendor-ui
???   ????????? vendor-ui
????????? SystemTests/TicketsTests                    // separate project holding E2E System Tests for Tickets
```