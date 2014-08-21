# fuzzy-dangerzone

Sample Java 8, Jersey, Spring 4, MongoDB


## Software Requirements

* JDK 8
* Maven 3.2.x
* Git
* Subversion

### Snapshot Versions

* aspectj-maven-plugin:1.7-SNAPSHOT
	* checkout from SVN http://svn.codehaus.org/mojo/trunk/mojo/aspectj-maven-plugin
	* install to local Maven repo using `mvn clean install`


## Basic usage

* Run the app using Jetty

		mvn clean package jetty:run

* Run Cucumber tests

		mvn clean verify


## Versions

* Java 8
* AspectJ 1.8
* Servlet 3.1
* Jersey 2.11
* Spring 4
* Spring Data 1.5
* Hibernate Validator 5.1
* Jetty 9.2

## Zero XML

The project uses Servlet3 and Spring Java based configuration.