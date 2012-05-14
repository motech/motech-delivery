#!/bin/sh

# to setup vm : wget https://raw.github.com/motech/motech-delivery/master/motech-delivery-scm/bootstrap.sh && sh ./bootstrap.sh 

echo "MoTeCH: Bootstrap Machine:"
rpmUrl="http://ftp.jaist.ac.jp/pub/Linux/Fedora/epel/6/"`uname -m`"/epel-release-6-6.noarch.rpm"
echo "Using epel release 6 : $rpmUrl"
cd /tmp && rpm -ivh "$rpmUrl"
yum -y install puppet && yum -y install git && \
cd /tmp/ && git clone git://github.com/motech/motech-delivery.git && \
cd /tmp/motech-delivery/motech-delivery-scm/puppet && vi manifests/nodes/configuration.pp && \
puppet apply manifests/site.pp --modulepath=modules/ && echo "Completed!"