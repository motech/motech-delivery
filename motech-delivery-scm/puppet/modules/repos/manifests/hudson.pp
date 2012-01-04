class repos::hudson {

  include repos::epel

  package { "python-hashlib":
    ensure  =>  "present",
    require => [Package["epel-release-5-4"]],
  }  

  yumrepo { hudson:
    descr    => 'Repo for hudson',
    baseurl  => 'http://hudson-ci.org/redhat/',
    enabled  => 1,
    require => Package["python-hashlib"],
    gpgcheck => 0,   
  }
}
