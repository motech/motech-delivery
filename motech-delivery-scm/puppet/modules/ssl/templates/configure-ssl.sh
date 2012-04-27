sed -i 's/\#LoadModule\ ssl_module\ modules\/mod_ssl.so/LoadModule\ ssl_module\ modules\/mod_ssl.so/g' /etc/httpd/conf.d/ssl.conf

sed -i 's/\#Include\ conf.d\/*.conf/Include\ conf.d\/*.conf/g' /etc/httpd/conf/httpd.conf

sed -i 's/\#SSLCertificateFile/SSLCertificateFile/g' /etc/httpd/conf.d/ssl.conf

sed -i 's/\#SSLCertificateKeyFile/SSLCertificateKeyFile/g' /etc/httpd/conf.d/ssl.conf


