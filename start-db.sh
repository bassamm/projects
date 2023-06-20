#!/bin/bash

# start container only if not running previously
PG_CONTAINER_RUNNING=$(docker container inspect -f '{{.State.Running}}' pg-container)
if [ "$PG_CONTAINER_RUNNING" != "true" ]; then
    echo "Starting pg-container..."
    sudo docker compose -f db/docker-compose.yml up -d
else
    echo "pg-container running..."
fi