node default {
 import "configuration"
 
 class{users : userName => "${motechUser}", password => "${motechPassword}" }	
	
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
 include monitor
}

import "dev"
import "ci"
