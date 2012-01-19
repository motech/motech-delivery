#!/bin/bash

master="<%= couchMaster %>"
dbs="<%= couchDbs %>"

p1='{"source": "http://'
p2=':5984/'
p3='","target": "'
p4='", "create_target": true, "continuous": true}'

for db in $dbs; {
    payload=$p1"$master"$p2"$db"$p3"$db"$p4
    curl -H 'Content-Type: application/json' -X POST http://localhost:5984/_replicate -d "$payload"
}


