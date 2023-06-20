#!/bin/bash

# starting database container
source start-db.sh

#build and run the backend application locally
./gradlew bootRun

