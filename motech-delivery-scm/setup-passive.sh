#!/bin/sh
wget https://github.com/motech/motech-delivery/raw/master/motech-delivery-scm/bootstrap.sh
chmod u=rx bootstrap.sh && ./bootstrap.sh
echo #setting up machine as couchdb slave