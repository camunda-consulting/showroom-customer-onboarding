# Camunda showcase "Insurance Application" / "Versicherungsneuantrag"

# What it does
This demo showcases a simplified insurance or bank account application process using the Camunda platform for workflow and decision automation.

The project includes a simple self-contained, custom-made web frontend, which can be used to submit applications.
After deploymnet the web pages are available under:

* English: http://localhost:8080/camunda/online/index.html?lang=en
* German: http://localhost:8080/camunda/online/index.html?lang=de

On the landing page an insurance or banking frontend and the language can be selected.

![Insurance homepage](docs/application.png)
Insurance application

![Banking homepage](docs/application_banking.png)
Bank account application

The frontends start instances of the follwoing (BPMN) process via a simple REST API:

![Workflow model](docs/workflow.png)
(src/main/resources/static/bpmn/customer_onboarding_en.bpmn)

The workflow first executes an automated decision (in DMN), determining if the application requires a manual assessment or can be process automatically:

![Decision model](docs/decision.png)
(src/main/resources/static/bpmn/risk_check_en.dmn)


The camunda web applications will be accessible under http://localhost:8080/camunda

The user/password demo/demo can be used to login.
 

# Architecture
The showcase uses Spring Boot and the Camunda Spring boot starters. 
https://docs.camunda.org/manual/latest/user-guide/spring-boot-integration

Spring web is used for the REST services defined in *ApplicationOnlineFacade*


![Insurance homepage](docs/projectLayout.png)


# How to build and run
1. clone project or download sources
2. create Camunda Cloud cluster and client credentials
3. update clusterId, clientId, and clientSecret in [application.yml](/src/main/resources/application.yml) 
4. build via Maven (*mvn clean package*)
5. run via Spring boot (*mvn spring-boot:run*) 

During startup the showcase will automatically deploy the process model
including the form to the configured cluster. 
