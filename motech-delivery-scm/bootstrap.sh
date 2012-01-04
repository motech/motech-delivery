#!/bin/sh

# to setup vm : wget https://github.com/motech/motech-delivery/raw/master/motech-delivery-scm/bootstrap.sh && ./bootstrap.sh

cd /tmp/ && wget http://download.fedoraproject.org/pub/epel/6/x86_64/epel-release-6-5.noarch.rpm && rpm -ivh /tmp/epel-release-6-5.noarch.rpm && \
yum -y install puppet && yum -y install git && \
cd /tmp/ && git clone git://github.com/motech/motech-delivery.git && \
cd /tmp/ &&  tar xzvf /tmp/master &&  \
cd /tmp/motech-motech-delivery*/motech-delivery-scm/puppet  && vi manifests/nodes/configuration.pp && \
puppet apply manifests/site.pp --modulepath=modules/
