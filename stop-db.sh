#!/bin/bash

usage()
{
	printf "%b" "Usage: $0 [-f|-h]\n"
	printf "%b" "  -f: forced removing the pg-container from HD\n"
	printf "%b" "  -h: display this help\n"
}

if [ $1 = "-h" ]
then
    usage
    exit 1
fi

# check database container running?
PG_CONTAINER_RUNNING=$(docker container inspect -f '{{.State.Running}}' pg-container)

if [ "$PG_CONTAINER_RUNNING" = "true" ]; then

    if [ $# -eq 0 ]
    then
        echo "Stopping pg-container using 'docker container stop pg-container'..."
        docker compose -f stop db/docker-compose.yml stop
    elif [ $1 = "-f" ]
    then
        echo "Removing pg-container/pgadmin4 using 'docker compose -f rm-database/docker-compose.yml pg-container down'..."
        docker compose -f db/docker-compose.yml down pg-container
    fi
fi
