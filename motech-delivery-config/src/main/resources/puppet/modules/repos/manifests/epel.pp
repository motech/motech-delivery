class repos::epel {
  file { "/usr/local/src/epel-release-6-5.noarch.rpm":
    source => "puppet:///modules/repos/epel-release-6-5.noarch.rpm"
  }

  package { "epel-release.noarch":
    provider => "rpm",
    ensure => "present",
    source => "/usr/local/src/epel-release-6-5.noarch.rpm",
    require => File["/usr/local/src/epel-release-6-5.noarch.rpm"]
  }
}
