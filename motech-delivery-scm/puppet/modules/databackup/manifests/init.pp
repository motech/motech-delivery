class databackup  ($couchDbBackupDir, $postgresBackupDir, $dataBackupDir, $dataBackupLocLink) {

    file { ["home/${motechUser}/${dataBackupDir}", "home/${motechUser}/${dataBackupDir}/${couchDbBackupDir}", "home/${motechUser}/${dataBackupDir}/${postgresBackupDir}"] :
        ensure => "directory",
    }

    file {"${dataBackupLocLink}" :
        ensure => "link",
        target => "home/${motechUser}/${dataBackupDir}",
    }

  }

}