# this is a workaround repo.
class repos::puppetlabs {
  yumrepo { puppetlabs:
    descr    => 'Puppetlabs repo',
    baseurl  => 'http://yum.puppetlabs.com/base/',
    enabled  => 1,
    gpgcheck => 0,
  }
}

# this is how the puppet repo should be set up
#class repos::puppetlabs {
#  file { "/usr/local/src/puppetlabs-repo-3.0-2.noarch.rpm":
#    source => "puppet://puppet/modules/repos/puppetlabs-repo-3.0-2.noarch.rpm"
#  }
#
#  package { "puppetlabs-repo":
#    provider => "rpm",
#    ensure => "present",
#    source => "/usr/local/src/puppetlabs-repo-3.0-2.noarch.rpm",
#    require => File["/usr/local/src/puppetlabs-repo-3.0-2.noarch.rpm"]
#  }
#}
