class databackup  ($couchDbBackupLink, $postgresBackupLink, $dataBackupDir, $machineType) {
    case $machineType {
        slave: {
            file {"${dataBackupDir}" :
                ensure => "directory",
            }

            file { "${couchDbBackupLink}" :
                ensure => "link",
                target => "/var/lib/couchdb",
            }

            file { "${postgresBackupLink}" :
                ensure => "link",
                target => "/usr/local/pgsql/data",
            }
        }
    }
}