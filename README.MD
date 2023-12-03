# About `Tickets`

## Business goal

Ticketing system is a cutting-edge concert tickets selling platform with a revolutionary pricing algorithm and super friendly for Concert Promoters to sell their concerts with a remarkable profit.

Please read full documentation here: [Tickets doc.pdf](https://drive.google.com/file/d/1wHNTqUuKC_To8y9hnwEq6-izZE2G6eSh/view?usp=sharing)

## Purpose

The system is built as a training example for [Testing Microservices](https://bottega.com.pl/szkolenie-ms-testing).

## Architecture

The overall System consists of many microservices. This repository holds a subset of them, essential for Promoter to create concerts. High level architecture is below.

![img.png](docs/TicketsArchitecture.jpg)

# Importing to IDE

You should import the `Tickets/build.gradle` as a root project to your IDE.

## Speed up test execution

Gradle has a bug, and it executes tests slowly. Use IntelliJ Idea for build and test execution. 

Go to: `Settings -> Build, Execution Deployment -> Build Tools -> Gradle` and set `Build and Run using` and `Run tests using` to value: `IntelliJ Idea` (not Gradle)

## Publish Spring Cloud Contracts

Some tests expect External services stubs managed by Spring Cloud Contract so those might fail until contracts are published to local maven repository because of AetherStubDownloader cannot download stubs. To publish contracts execute: `./gradlew publishToMavenLocal` in `Tickets/` or run IntelliJ configs named `Promoter/Pricing contract publish`


## Verify after import

Execute tests to check if the system is working on your machine and tests are green.

I suggest running IntelliJ task `Tickets test --all` to run all kinds of tests in the system. Alternatively you can run `./gradlew test` for all the services. Follow this doc to find out details.

Reach me out in case of any difficulties.


## Known issues

On Windows, Kafka Docker in Test Container setup might be unstable or unpredictable. When you encounter this happening, just drop it for now. Make sure `unit`, and `Api` tests are green.


# Running

Both Gradle and IntelliJ run configurations are available.

Those are fine-grained configurations starting apps and executing tests on a desired level (Micro, Unit, Component,
Dependency, Api, Contract, GUI, System)


## Starting off the whole Tickets system

Tickets System consists of several services you need to run to see it working. See running instructions below:

| Service      | IntelliJ Idea task | Manual command                                                          |
|--------------|--------------------|-------------------------------------------------------------------------|
| Kafka        | `kafka run`        | run `docker-compose.yml` using `docker compose up` under `infra/kafka/` |
| Promoter GUI | `promoter-ui run`  | `ng serve` from `promoter-ui/`                                          |
| Promoter     | `Promoter run`     | `./gradlew bootRun` from `Promoter/`                                    |
| Pricing      | `Pricing run`      | `./gradlew bootRun` from `Pricing/`                                     |

# Run and test configurations

## Gradle

Execute gradle tasks by executing `./gradlew <task-name>`, where task names are defined
in `services/<service>/gradle/test-suites.gradle`. To run all tests at once use task `test`

Tasks available for Tests are as follows

Note: when running tests from IntelliJ, a small popup might appear asking which test task to execute (test, micro, unit, ...) - always select `test`, which runs all test levels

### Running `Promoter`, `Pricing` and `SharedLib`

- `bootRun`: starts the service
- `microTest`: runs suites suffixed with `microTest` and `microSpec`
- `unitTest`: runs suites suffixed with `unitTest` and `unitSpec`
- `compTest`: runs suites suffixed with `compTest` and `compSpec`
- `depTest`: runs suites suffixed with `depTest` and `depSpec`
- `apiTest`: runs suites suffixed with `apiTest` and `apiSpec`
- `contractTest`: runs contract tests defined in `/src/contractTests/resources/contracts`

Note: `SharedLib`, as a utils library, contains only `microTest`s,

#### Prerequisites

Installed:

- gradle
- JDK 17+
- IDE (recommended: IntelliJ Idea)
- git
- docker
- docker compose

Operating System: Ubuntu (recommended). `Tickets` is also ready to run on Mac and Windows, however docker on Windows is unpredictable.

### Running `promoter-ui`

As an Angular project the task config is different here.

- `ng serve`: runs app on under `localhost:4200`
- `protractor conf.js` executes isolated GUI tests on `promoter-ui`. Note: execute from `services/promoter-ui/src/e2e`

#### Prerequisites

Installed:

- node.js `sudo apt install nodejs`
- Angular `npm install -g @angular/cli`
- Protractor `npm install -g protractor`
- Chrome Browser

Before start, install node dependencies by running `npm install` from `services/promoter-ui`

### Running `TicketingTests`

The project holds System Tests only, all under `src/test` with gradle task:

- `test`: runs tests against the Tickets System

## Run configs in IntelliJ Idea

All the configurations listed above are also exported to this repo as an IntelliJ Idea configurations. They reside
in `.run` directory and should be automatically imported once `Tickets` project is imported.

Some run configurations are using [Multirun plugin](https://plugins.jetbrains.com/plugin/7248-multirun) to start all microservices in the system. Consider installing the plugin: 

# Tests

## `Pricing` and `Promoter` Microservices

- All internal tests are under `src/test/java`
- Contracts are under `src/contractTest/resources`
- Generated contract tests are in `build/generated-test-sources/contractTest`

## `promoter-ui`

Protractor tests are located under `src/e2e/test`

## `SharedLib` and `TicketsTests`

- All tests are under `src/test/java`

# Promoter API Stubs

To start Promoter Stub, based on Contract tests, see [README](services/Promoter/api-stubs/README.MD)

# Project Structure

Important paths in project are:

```
Tickets
└── infra/kafka/docker-compose.yml              // Use to start Apache Kafka in docker on your machine
└── libs/SharedLib                              // Java library embedded in services
└── services                                    // Microservices building up Tickets system
│   └── Pricing
│   └── Promoter
|       └── api-stubs/run-contract-stubs.sh     // execute to run Promoter API Stubs based on Contract Tests to test promoter-ui
│   └── promoter-ui
└── SystemTests/TicketingTests                    // separate project holding E2E System Tests for Ticketing
```

# Branching and Tagging

Complete solution is on the `main` branch. Training assignments are on `training_tags` branch. Latter branch has tags corresponding to the tasks and their steps, where part of code from `main` is missing and there's a room for exercising.

All feature development is made in branches as follows:
1. start new branch from `main`
2. When finished, Issue PR to `main`, squashing commits.
3. `training_tags` branch should be rebased on `main` to reflect new features in assignments.
   - Important: on a regular rebase, tags are not moved. To reflect rebased changes and migrate tags, use https://github.com/maastiff/git-rebase-autotags
   - check if hook is present in https://github.com/maastiff/git-rebase-autotags#locally-in-repository
   - check if it is enabled as in https://github.com/maastiff/git-rebase-autotags#configure