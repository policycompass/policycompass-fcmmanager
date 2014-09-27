Policy Compass FCM Manager Service
==================

## Requirements

### Install Java
* If you currently do not have java, you can download it quite easily with apt-get.
```shell
  sudo apt-get install default-jdk
```
### Install Tomcat
* To download tomcat from their site, copy the link for the tar.gz package under the “Core” section and begin the download. You will get a link that originates from one of Apache’s many mirrors, making the command look mostly like this (although coming from a different site).
```shell
  wget http://mirror.vorboss.net/apache/tomcat/tomcat-7/v7.0.55/bin/apache-tomcat-7.0.55.tar.gz
```
After the download completes, untar the file.
```shell
  tar xvzf apache-tomcat-7.0.55.tar.gz
```
Finish up the Tomcat installation by moving the files to a convenient directory.
```shell
  sudo mv apache-tomcat-7.0.55  /usr/share/
```
### Configure .bashrc
Before editing the .bashrc file in your home directory, we need to find the path where Java has been installed to set the JAVA_HOME environment variable. Let's use the following command to do that:
```shell
  update-alternatives --config java
```
The complete path displayed by this command is:
/usr/lib/jvm/java-7-openjdk-amd64/jre/bin/java

The value for JAVA_HOME is everything before /jre/bin/java in the above path - in this case, /usr/lib/jvm/java-7-openjdk-amd64. Make a note of this as we'll be using this value in this step and in one other step.

In order to start Tomcat, we need to add it as an environment variable in the /.bashrc file.
```shell
  sudo gedit ~/.bashrc
```
You can add this information to the end of the file:
```shell
  export JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64
  export CATALINA_HOME=/usr/share/apache-tomcat-7.0.55
```
After saving and closing the .bashrc file, execute the following command so that your system recognizes the newly created environment variables:
```shell
  source ~/.bashrc
```
The final step is to activate Tomcat by running its startup script:
```shell
  $CATALINA_HOME/bin/startup.sh
```
You can visually verify that Tomcat is working by accessing your server page at http://localhost:8080.
### Install Maven
```shell
  sudo apt-get install maven
```
### Install PostgreSQL
```shell
  sudo apt-get install postgresql
```

Create a postgres user and a database

```shell
  sudo -u postgres createuser pcompass -W
  sudo -u postgres createdb pcompass --owner pcompass
```

## FCM Manager Web Service Installation

* Clone the repository
```shell
  git clone git@github.com:policycompass/policycompass-fcmmanager.git
  cd policycompass-fcmmanager
```
* Edit the Database settings according to your needs
```shell
  gedit src/hibernate.cfg.xml
```
* Create WAR file 
```shell
  mvn clean install
```
* Deploy to local Tomcat
```shell
  mv target/ $CATALINA_HOME/webapps
```

### Test the Web Service
http://localhost:8080
