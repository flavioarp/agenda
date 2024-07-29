#!/usr/bin/env sh

## dependencies installation and apache tomcat setup

# Enable systemd on WSL
# 1) echo -e "[boot]\nsystemd=true" > ./wsl.conf
# 2) sudo mv wsl.conf /etc/wsl.conf
# 3) exit
# 4) "wsl --shutdown" on win32

sudo dnf install tomcat java-11-openjdk-devel gnupg mariadb mariadb-server
sudo alternatives --set java java-11-openjdk.x86_64 # alternatively you can "sudo alternatives --config java"
sudo systemctl start tomcat
sudo systemctl start mariadb

## mariadb setup

sudo mysql_secure_installation

# Switch to unix_socket authentication [Y/n] n
# Change the root password? [Y/n] y
# Remove anonymous users? [Y/n] y
# Disallow root login remotely? [Y/n] y
# Remove test database and access to it? [Y/n] y
# Reload privilege tables now? [Y/n] y

sudo mysql -u root -p

# 1) Create DBA user with % privilege on maridb shell (clear: <CTRL + L>)
# MariaDB [(none)]> create user 'dba'@'%' identified by <SINGLE_QUOTES_PASSWORD>;
# 2) Commit changes
# MariaDB [(none)]> flush privileges;
# 3) Verify changes
# MariaDB [(none)]> select user,host from mysql.user;
# 4) Grant all databases (*) and all tables (*) privilege to DBA user
# MariaDB [(none)]> grant all privileges on *.* to 'dba'@'%';
# 5) Commit changes
# MariaDB [(none)]> flush privileges;
# 6) Exit mariadb shell with <CTRL + D>

sudo mkdir /var/lib/tomcat/webapps/ROOT
