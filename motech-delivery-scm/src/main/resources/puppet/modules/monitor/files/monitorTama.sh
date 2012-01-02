#!/bin/bash

ENV="production"
phone_numbers="9880xxxxxx 9845xxxxxx"
api_key="KK76eca1f1a1229c2cc44ee858843b426d"



check_processes_n_ports() {
#couchdb
  pgrep couchdb>/dev/null;                                         if [ $? != 0 ] ; then addMessage "Couch db is down"; fi
  netstat -nplt 2>/dev/null |grep ':5984 ' >/dev/null                                 || addMessage "Couchdb is not listening";
#mysqld
  pgrep mysqld>/dev/null;                                          if [ $? != 0 ] ; then addMessage "My sql database is down"; fi
  netstat -nplt 2>/dev/null |grep ':3306 ' >/dev/null                                 || addMessage "Mysql Service is not listening";
  service httpd   status    |grep running>/dev/null;               if [ $? != 0 ] ; then addMessage "Http server is down"; fi
#tomcat 
  pgrep -f 'org.apache.catalina.startup.Bootstrap start'>/dev/null;if [ $? != 0 ] ; then addMessage "Tomcat is down"; fi
  netstat -nplt 2>/dev/null |grep ':8080  '>/dev/null                                 || addMessage "Tomcat is not listening";
#active mq
  pgrep -f 'apache-activemq-5..../bin/run.jar start'>/dev/null;    if [ $? != 0 ] ; then addMessage "Active MQ is down"; fi
  netstat -nplt 2>/dev/null |grep ':61616 '>/dev/null                                 || addMessage "Active MQ Service is not listening";
}

alertMessage="";

addMessage () {
  if [ "$alertMessage" = "" ] 
  then
    alertMessage="This is alert from Taama $ENV, "$1;
  else
    alertMessage+=" and "$1;
  fi
}

callAndNotify() {
  for phone in $phone_numbers; {
    echo "calling $phone";
    curl --data "phone_no=$phone&api_key=$api_key&extra_data=<response><playtext>$1</playtext><hangup/></response>" http://www.kookoo.in/outbound/outbound.php
  }
}

sendSMS() {
  for phone in $phone_numbers; {
    echo "sending msg to $phone";
    curl --data "phone_no=$phone&api_key=$api_key&message=$1" http://www.kookoo.in/outbound/outbound_sms.php
  }	
}


PATH=$PATH:/sbin
check_processes_n_ports

if [ "$alertMessage" != "" ]
then
   echo "$alertMessage"
   sendSMS $alertMessage
   callAndNotify "$alertMessage"
else
   echo `date +"%F|%T"` " | System status : ok";
fi




