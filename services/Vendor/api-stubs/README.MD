Circle K ID API Contract
=

# Workflow

## Definitions

Producer: Vendor

Consumer: Vendor's API Client

## Define new contract

Workflow is as follows:
1. Define the new API as a contract in `/src/contractTest/resources/contracts` using Groovy DSL
2. Issue a Pull Request with the new definition to discuss it and refine
3. Implement the Consumer and API Producer
4. When Producer implementation is ready, Consumer can switch to the real implementation   

## API Mocks (Contract Stubs)

API Contract Stubs are prepared to agree on the API details between API Producer and API Consumers.
API Contract is defined in groovy files where request and response specifications are specified. Spring Cloud Contract based on those files generates automated tests For Producer and Mock API server for Consumers. 
Those Stubs are created before the actual Producer implementation is done. This way Consumer does not have to wait for the real implementation. They can work on API mocks on their side.

## Producer implementation

Producer will implement this contract on its side as there are automated tests generated from contract definitions. This will guarantee that Producer's implementation will be in line with the contract stubs. 
  
## Consumer implementation
 
Consumer can start Mock Server based on WireMock that will expose APIs defined as Contracts.

# How to

## API Consumer

### Run API Mock on local machine

As an API Consumer you need to have the server hosting the API. You will run it on localhost with WireMock under the hood.

1. Checkout Producer repository
2. Navigate to `/api-stubs`
3. Run `./run-contract-stubs.sh`
4. Check if API is running by calling `GET` <http://localhost:8080/health>

The `./run-contract-stubs.sh` will use the contract definitions from `/src/contractTest/resources/contracts` and will start mock server on port stored under `API_PORT` variable in `./run-contract-stubs.sh` file (default: `8080`). 

When running `./run-contract-stubs.sh`, the gradle task `publishToMavenLocal` is executed as a startup step. Contracts are built, packaged into jar, and published. From there the Stub Runner is taking the API definitions.

### View all exposed APIs

To see all APIs exposed by a mock server, open <http://localhost:8080/__admin/mappings> in your browser

### Request contract changes

If the API does not suit you:

1. Edit the contract definitions from `/src/contractTest/resources/contracts` according to your needs
2. Issue a Pull Request with the changes

Note that you don't have to wait until Producer implements your changes. You can restart your local Mock Server and work on a new API instantly.

## Producer

### Generate tests from contracts

To create or update the contract:
1. Prepare the API definition under `/src/contractTest/resources/contracts`
2. run 
```
./gradlew clean :Vendor:generateContractTests
``` 
from repository root
3. Find the Generated Contract test under `/build/generated-test-sources/contracts/`

#### Run contract tests

After they're generated, can be run with 
```
./gradlew :Vendor:contractTest
```

### Test naming convention

#### Test File

Default Spring Cloud Contract naming convention is in use. The Generated Contract test will be named based on the `resources` package name. So if contract definition file is in `/src/contractTest/resources/contracts/Vendor` package, then the generated test will be named `VendorTest`. 

#### Test base class

Similar naming convention applies to Test Base class that is extended by all generated tests. If contract definition file is in `/src/contractTest/resources/contracts/Vendor` package, then the base class should be named `VendorBase`.

### Avoid test fails when API implementation is not ready yet

If work on contract is in progress, you can call `inProgress()` in your `Contract.make()` to prevent generation of Producer Contract test. This way Consumers can work on mocks, and your build is green.  

### Configuration

See `/Vendor/gradle/spring-cloud-contract.gradle` file for configuration details.