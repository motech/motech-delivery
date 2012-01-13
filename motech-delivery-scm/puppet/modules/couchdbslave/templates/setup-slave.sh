#!/bin/bash

dbs="<%= dbNames %>"
master="<%= $masterServer %>"

for db in $dbs; {
    echo $db;
    curl -H 'Content-Type: application/json' -X POST http://localhost:5984/_replicate -d '{"source": "http://$master:5984/$db", "target": "rules", "create_target": true, "continuous": true}'
}



