class repos::epelcouchdb {

	file { "/etc/yum.repos.d/epel-couchdb.repo":
		source => "puppet:///modules/repos/epel-couchdb.repo",
	}
}