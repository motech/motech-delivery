#!/bin/sh

# to setup vm : wget https://github.com/motech/motech-delivery/raw/master/motech-delivery-scm/bootstrap.sh && sh ./bootstrap.sh

echo "# MoTeCH: Bootstrap Machine:\n# sh ./bootstrap.sh url_for_epel_rpm"
echo "# EL5-32bit - http://download.fedoraproject.org/pub/epel/5/i386/epel-release-5-4.noarch.rpm"
echo "# EL6-32bit - http://download.fedoraproject.org/pub/epel/6/i386/epel-release-6-5.noarch.rpm"
echo "# EL6-64bit - http://download.fedoraproject.org/pub/epel/6/x86_64/epel-release-6-5.noarch.rpm"

cd /tmp/ && wget $1 --output-document=epel-release.rpm && rpm -ivh /tmp/epel-release.rpm && \
yum -y install puppet && yum -y install git && \
cd /tmp/ && git clone git://github.com/motech/motech-delivery.git && \
cd /tmp/motech-delivery/motech-delivery-scm/puppet  && vi manifests/nodes/configuration.pp && \
puppet apply manifests/site.pp --modulepath=modules/
