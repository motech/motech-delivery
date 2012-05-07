 #-----------------------------MOTECH BOOTSTRAP---------------------------------------
 #  Save and exit to continue with setup.
 #------------------------------------------------------------------------------------

 #--------------------------------SETTINGS--------------------------------------------
 # operatingsystem
 $os = "centos5" #[centos5 | centos6]
 $word = "32b" #[32b,64b]
 $arch = "x64" # [x64|i586]

 # user
 # to generate password hash use 'echo "password" | openssl passwd -1 -stdin'
 $motechUser = "motech"
 $motechPassword = '$1$IW4OvlrH$Kui/55oif8W3VZIrnX6jL1'

 # mysql
 $mysqlPassword = "password"

 # monitor
 $monitor_adminPhoneNumbers = "9880xxxxxx 9880xxxxxx"
 $monitor_kookooKey = "xxx"
 $monitor_environment = "production"
 $monitor_app_url = "http://localhost:8080/tama/login"

 # couchdb
 $couchMachine = "master" #[master | slave]
 $couchMaster = "127.0.0.1"
 $couchDbs = "tama-web ananya"
 $couchDbBackupLink = "/opt/backups/couchdb"
 #$couchDbDataLocation=/var/lib/couchdb


 # postgres
 $postgresUser="postgres"
 $postgresPassword= '$1$IW4OvlrH$Kui/55oif8W3VZIrnX6jL1'
 $pgpoolPassword = '5f4dcc3b5aa765d61d8327deb882cf99'

 $postgresMachine = "master" #[master | slave]
 $postgresMaster = "127.0.0.1"
 $postgresSlave = "127.0.0.1"
 $postgresBackupLink = "/opt/backups/postgres"
 #$postgresDataLocation=/usr/local/pgsql/data

 # data backup
 $dataBackupDir = "/opt/backups"
 $machineType = "master" #[master | slave]

 # activemq
 $activemqMachine = "master" #[master | slave]
 $activemqMasterHost = "127.0.0.1"
 $activemqMasterPort = 61616

 # httpd
 $httpdMachine = "regular" #[regular | failoverProxy] Running in slave sets up the
 $httpdProxyPort = 8082
 $httpdMasterHost = "127.0.0.1"
 $httpdMasterPort = "80"
 $httpdSlaveHost = "127.0.0.1"
 $httpdSlavePort = "80"
 
 # https
 $SSLCertificateFile = "/etc/pki/tls/certs/localhost.crt"
 $SSLCertificateKeyFile = "/etc/pki/tls/private/localhost.key"

 #--------------------------------RESOURCES--------------------------------------------
 # comment out resources not required to be installed

 # class{users : userName => "${motechUser}", password => "${motechPassword}" }
 # class{couchdb : couchMaster => "${couchMaster}", couchDbs => "${couchDbs}", couchMachine => "${couchMachine}" }
 # class{postgres : postgresUser => "${postgresUser}", postgresPassword => "${postgresPassword}", postgresMachine => "${postgresMachine}", postgresMaster => "${postgresMaster}", postgresSlave => "${postgresSlave}"}
 # class{postgres_pgpool : postgresUser => "${postgresUser}", postgresPassword => "${postgresPassword}", postgresMachine => "${postgresMachine}", postgresMaster => "${postgresMaster}", postgresSlave => "${postgresSlave}"}
 # class{databackup : couchDbBackupLink => "${couchDbBackupLink}", postgresBackupLink => "${postgresBackupLink}", dataBackupDir => "${dataBackupDir}", machineType => "${machineType}"}
 # class { activemq : activemqMachine => "${activemqMachine}", activemqMasterHost => "${activemqMasterHost}", activemqMasterPort => "${activemqMasterPort}" }
 # class { httpd : httpdMachine => "${httpdMachine}", httpdProxyPort => "${httpdProxyPort}", httpdMasterHost => "${httpdMasterHost}", httpdMasterPort => "${httpdMasterPort}", httpdSlaveHost => "${httpdSlaveHost}", httpdSlavePort => "${httpdSlavePort}" }

 # include git
 # include httpd    
 # include ant
 # include mysql
 # include mysqlserver
 # include asterisk
 # include sox
 # include activemq
 # include tomcat
 # include duplicity
 # include motechquartz
 # include monitor
 # include ssl
 # include verboice
 #  include doxygen

