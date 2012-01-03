node default {
 $motechUser = "motech"
 $mysqlPassword = "password"
 
 $monitor-adminPhoneNumbers = "9880xxxxxx 9880xxxxxx"
 $monitor-kookooKey = "KK76eca1f1a1229c2cc44ee858843b426d"
 $monitor-environment = "production"
 
 class{users : userName => "${motechUser}" }	
	
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
