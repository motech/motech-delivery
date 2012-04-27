sed -i 's/\#LoadModule\ ssl_module\ modules\/mod_ssl.so/LoadModule\ ssl_module\ modules\/mod_ssl.so/g' /etc/httpd/conf.d/ssl.conf

sed -i 's/\#Include\ conf.d\/*.conf/Include\ conf.d\/*.conf/g' /etc/httpd/conf/httpd.conf

sed -i 's/\#SLCertificateFile/SLCertificateFile/g' /etc/httpd/conf.d/ssl.conf

sed -i 's/\#SLCertificateKeyFile/SLCertificateKeyFile/g' /etc/httpd/conf.d/ssl.conf

