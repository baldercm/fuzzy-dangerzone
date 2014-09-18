# fuzzy-dangerzone

Sample webapp using Java 8, Jersey, Spring 4, MongoDB, Docker, AWS


## Overview

* [Software Requirements](#software-requirements)
* [Basic Usage](#basic-usage)
* [REST API](#rest-api)
* [Executable JAR webapp](#executable-jar-webapp)
* [Docker](#docker)
* [Cucumber Tests](#cucumber-tests)
* [AWS Elastic Beanstalk](#aws-elastic-beanstalk)
* [Versions](#versions)


## Software Requirements

* Java 8 (1.8.0_20)
* MongoDB (2.6.4)
* Maven (3.2.3)
* Docker (lxc-docker 1.2.0)
* Git

### Automatic Installation

Check https://github.com/balder-otium360/devenv (only for Ubuntu) to help you install all the required software or download and install the software manually.


## Basic Usage

* Run the app using embedded Jetty:

        mvn clean compile exec:java
* Run Cucumber tests on Docker:

        mvn clean verify -Pcucumber
* Upload to AWS Elastic Beanstalk:

        mvn clean deploy -Paws


## REST API

All the resource URIs consumes and produces `application/json`.

* `GET  http://localhost:8080/poc/api/sample` find all the existing samples
* `POST http://localhost:8080/poc/api/sample` creates a new sample
* `GET  http://localhost:8080/poc/api/sample/async` find all the existing samples using Servlet3 AsyncResponse


## Executable JAR webapp

The project is packaged by the `maven-shade-plugin` as an executable JAR webapp using an embedded Jetty server. The main class is `org.baldercm.poc.Main`.

### Zero XML

The project uses Java configuration for Servlet3, Jersey and Spring.


## Docker

The project contains a `src/main/docker` folder that holds the required `Dockerfile`s and start/stop scripts.

To build a fully functional Docker environment, simply use

    mvn clean package

You will get something like this:

    target/docker
    ├── docker-start.sh
    ├── docker-stop.sh
    ├── app
    |   ├── Dockerfile
    |   ├── poc.jar
    ├── mongodb
    |   ├── Dockerfile

You can run the Docker containers using

    sh target/docker/docker-start.sh

You can access the webapp pointing your browser or REST client to

    http://localhost:8080/poc/api/sample
(if you have no data you will only see `[]`, an empty JSON array).

To stop and remove all Docker containers and images use

    sh target/docker/docker-stop.sh

### Docker assembly

The project uses `maven-assembly-plugin` to create different compressed files that contains the `target/docker` directory, generating the following files:

    target
    ├── poc-${version}-docker.tar.gz
    ├── poc-${version}-docker.zip

## Cucumber Tests

To run Cucumber tests on Docker use:

        mvn clean verify -Pcucumber

`exec-maven-plugin` is bound to `pre-integration-test` and `post-integration-test` phases to start and stop all Docker containers using the scripts in `target/docker`.

Cucumber tests are isolated in the Maven `cucumber` profile, entrypoint class is `org.baldercm.poc.RunCucumber` and tests are run with `maven-failsafe-plugin` during `integration-test` phase.


## AWS Elastic Beanstalk

You need an AWS Access Key to upload the application to AWS and setup Maven to manage the access keys when needed. Check [Beanstalker documentation](http://docs.ingenieux.com.br/project/beanstalker/aws-config.html) for a step-by-step configuration guide.

Tu upload to AWS Elastic Beanstalk use:

        mvn clean deploy -Paws

All AWS functionality is isolated in the Maven `aws` profile. The project uses `beanstalk-maven-plugin` to upload, create application version and update the environment.

### MongoDB for AWS deployment

MongoDB connection values for AWS are defined in `src/main/filters/aws.properties`. Current setup uses a MongoLab instance hosted on Amazon AWS. Feel free to update this values to whatever you need.


## Versions

* Java 8
* AspectJ 1.8
* Servlet 3.1
* Jersey 2.11
* Spring 4
* Spring Data 1.5
* Hibernate Validator 5.1
* Jetty 9.2