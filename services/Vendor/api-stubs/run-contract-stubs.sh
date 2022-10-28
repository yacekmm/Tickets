#!/usr/bin/env bash

API_PORT=8080

VERSION="3.1.2"
URL="https://search.maven.org/remotecontent?filepath=org/springframework/cloud/spring-cloud-contract-stub-runner-boot/${VERSION}/spring-cloud-contract-stub-runner-boot-${VERSION}.jar"
JAR_NAME="stub-runner-boot-${VERSION}"
JAR_LOCATION="stub-runner/${JAR_NAME}.jar"

function download_stub_runner() {
  if test -f "$JAR_LOCATION"; then
    echo "Using already existing stub-runner file $JAR_NAME"
  else
    echo "Downloading stub-runner"
    mkdir stub-runner
    wget -O $JAR_LOCATION $URL

    if [ $? -ne 0 ]; then
      echo "Stub Runner download failed"
      exit 1
    fi
  fi
}

function generate_fresh_stub_definitions() {
  echo "Generating stubs from contract definitions"
  cd ..
  ./gradlew publishToMavenLocal

  if [ $? -ne 0 ]; then
    echo "contract build failed"
    exit 1
  fi
  cd api-stubs
}

function run_stubs() {
  echo "Running stub runner"
  java -jar "${JAR_LOCATION}" --stubrunner.stubs-mode="local" --stubrunner.ids="com.bottega:Vendor:+:stubs:${API_PORT}" --server.port=8079
}


download_stub_runner
generate_fresh_stub_definitions
run_stubs