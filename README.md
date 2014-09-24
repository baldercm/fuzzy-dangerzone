# fuzzy-dangerzone

Sample webapp using Java 8, Jersey, Spring 4, MongoDB, Docker, AWS Beanstalk


## Overview

* [Software Requirements](#software-requirements)
* [Basic Usage](#basic-usage)
* [Executable JAR webapp](#executable-jar-webapp)
* [Docker](#docker)
* [Cucumber Tests](#cucumber-tests)
* [AWS Elastic Beanstalk](#aws-elastic-beanstalk)
* [REST API](#rest-api)
* [Versions](#versions)


## Software Requirements

* Java 8 (1.8.0_20)
* MongoDB (2.6.4)
* Maven (3.2.3)
* Docker (lxc-docker 1.2.0)

### Automatic Installation

Check https://github.com/balder-otium360/devenv (only for Ubuntu) to help you install all the required software or download and install the software manually.


## Basic Usage

* Run the app using embedded Jetty:

        mvn clean compile exec:java
* Run Cucumber tests on Docker:

        mvn clean verify -Pcucumber
* Upload to AWS Elastic Beanstalk:

        mvn clean deploy -Paws


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

Cucumber tests are isolated in the Maven `cucumber` profile, and tests are run with `maven-failsafe-plugin`.


## AWS Elastic Beanstalk

Tu upload to Amazon AWS' Elastic Beanstalk use:

        mvn clean deploy -Paws

You need an _AWS access key_ to upload the application to AWS and you must setup Maven to manage the access keys. Check [beanstalk-maven-plugin documentation](http://docs.ingenieux.com.br/project/beanstalker/aws-config.html) for a step-by-step configuration guide.

The project uses `beanstalk-maven-plugin` to upload the application to S3, create application version and update the Beanstalk environment.

All AWS functionality is isolated in the Maven `aws` profile. This profile will prevent the creation of default Docker assemblies and will create a special `poc-${version}-aws.zip` assembly instead:

    poc-${version}-aws.zip
    ├── Dockerfile
    ├── poc-${version}.jar

You can generate the AWS assembly to be manually uploaded and deployed later with:

        mvn clean package -Paws

The AWS assembly can be directly deployed on Beanstalk and consists of a single Java container, MongoDB instance must be provided separately.

### MongoDB for AWS deployment

MongoDB connection values for AWS are defined in `src/main/filters/aws.properties`. Current setup uses a MongoLab instance hosted on AWS. Feel free to update this values to whatever you need.

## REST API

All the resource URIs consumes and produces `application/json`.

* `GET  http://localhost:8080/poc/api/sample` find all the existing samples
* `POST http://localhost:8080/poc/api/sample` creates a new sample
* `GET  http://localhost:8080/poc/api/sample/async` find all the existing samples using Servlet3 AsyncResponse

## Versions

* Java 8
* AspectJ 1.8
* Servlet 3.1
* Jersey 2.12
* Spring 4.1
* Spring Data 1.6
* Hibernate Validator 5.1
* Jetty 9.2
