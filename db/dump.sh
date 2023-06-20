#!/bin/sh

#can be used to create a backup sql file of the database
docker exec -t pg-container pg_dumpall -c -U postgres > dump_`date +%d-%m-%Y"_"%H_%M_%S`.sql
