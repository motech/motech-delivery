node default {
 import "configuration"
 
 class{users : userName => "${motechUser}", password => "${motechPassword}" }	
 class{couchdbslave : $masterServer => "${$masterServer}", dbNames => "${dbNames}" }

 include java
 include git
 include httpd
 include ant
 include couchdb
 include mysql
 include mysqlserver
 include activemq
 include tomcat
 include duplicity
 include motechquartz
}
