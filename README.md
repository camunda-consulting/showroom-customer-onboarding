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
2. build via Maven (*mvn clean package*)
3. run via Spring boot (*mvn spring-boot:run*) 

During startup the showcase **will generate test data**. This can take a few minutes.
You can already use the application during this time.
To start with a fresh database delete /camunda-db.mv.db and /camunda-db.trace.db.

Alternatively you can play online in the [Showroom](http://showroom.camunda.com/) without installing anything locally. The showroom is password protected and only accessible for camunda partners.

# Camunda Cloud Showcase

The 'camunda-cloud' branch of this repository contains a version of the showcase project that works with Camunda Cloud. Any instructions or documentation you read below this point are exclusively related to Camunda Cloud.
# Instructions

1. If you do not have your own Camunda Cloud account and cluster, you will need to create both. Reference the [Camunda Cloud Getting Started Guide](https://camunda.com/blog/2019/09/getting-started-camunda-cloud/) for further instructions.

1. Once you have an account and have created a cluster, click on your cluster within Cloud Console. You should see something similar to the image below. 
![Cloud Console](docs/cloud_console_screenshot.png)

1. You now need to create API credentials so that your client code is able to connect to your cluster. Click on 'API' then the blue 'Create' button. Name your client, and ensure the checkboxes labelled 'Zeebe' and 'Tasklist' are both checked. Click 'Create' again and download the credentials for the next steps.

1. Clone and start process application:
   
   1. Clone the 'camunda-cloud' branch of this repository into a local folder `<process-app-dir>`.
   
   1. Create a copy `setup-secrets.sh` of `setup-secrets-template.sh`
   
          cp setup-secrets-template.sh setup-secrets.sh
      
      and fill it with mail server password and the credentials you just downloaded.

   1. Use your OS's mechanism to load the contents of this file as environmental variables. In Linux/mac os this works with

          source setup-secrets.sh
   
   1. Start the application:

          mvn spring-boot:run  -Dmaven.test.skip=true
      
      This will deploy the process to zeebe and start some job workers, also it published the UI.
      
      Hint: Make sure that you did not modify the credential part in application.yml in the project, this might overwrite the settings from `setup-secrets.sh` and connection can fail.

1. Start mail job worker:

   1. In a new terminal, navigate to `<process-app-dir>/external-task-worker`.

   1. Load credentials as env variables and start the worker via node

          source ../setup-secrets.sh
          npm install
          node external-worker.js

1. Clone and start DMN worker:

   1. In a new terminal, clone the ['zeebe-dmn-worker' community extension repository](https://github.com/camunda-community-hub/zeebe-dmn-worker) and navigate into the folder.

   1. Load credentials as env variables and start the worker

          source /<process-app-dir>/setup-secrets.sh
          mvn spring-boot:run  -Dmaven.test.skip=true

      Hint: Make sure that you did not modify the credential part in application.yml in the project, this might overwrite the settings from `setup-secrets.sh` and connection can fail.

1. Navigate to
   * [http://localhost:8080/camunda/online/banking/index.html?lang=en](http://localhost:8080/camunda/online/banking/index.html?lang=en) for Banking UI, or 
   * [http://localhost:8080/camunda/online/insurance/index.html?lang=en](http://localhost:8080/camunda/online/insurance/index.html?lang=en) for Insurance UI, or
   * [http://localhost:8080/camunda/online/telco/index.html?lang=en](http://localhost:8080/camunda/online/telco/index.html?lang=en) for Telco UI.
   
   Congratulations! Submit an application or two and check out your live processes by launching Operate from your Camunda Cloud SaaS cluster.












