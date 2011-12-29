class couchdb {
  
  include repos::epelcouchdb
   
  package { "couchdb":
    ensure  =>  "present",
    require => File["/etc/yum.repos.d/epel-couchdb.repo"],
  }

  service { "couchdb":
    ensure     => running,
    enable     => true,
    hasrestart => true,
    hasstatus  => true,
    require => Package["couchdb"],
  }
}
