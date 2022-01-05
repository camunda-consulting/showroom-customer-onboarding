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
## Instructions

1) If you do not have your own Camunda Cloud account and cluster, you will need to create both. Reference the [Camunda Cloud Getting Started Guide](https://camunda.com/blog/2019/09/getting-started-camunda-cloud/) for further instructions.

2) Once you have an account and have created a cluster, click on your cluster within Cloud Console. You should see something similar to the image below. 

![Cloud Console](docs/cloud_console_screenshot.png)

3) You now need to create API credentials so that your client code is able to connect to your cluster. Click on 'API' then the blue 'Create' button. Name your client, and ensure the checkboxes labelled 'Zeebe' and 'Tasklist' are both checked. Click 'Create' again and either download or note your credentials somewhere safe for later use.

4) Clone the 'camunda-cloud' branch of this repository to your local computer.

5) (You can skip this part if you set up with docker, see below) Find the 'application.yaml' file located in src/main/resources and update the clusterId, clientId, and clientSecret values to reflect your recently created API credentials. Do not commit these changes to a public repository unless you are comfortable with other users potentially accessing your Camunda Cloud cluster.

6) Clone the ['zeebe-dmn-worker' community extension repository](https://github.com/camunda-community-hub/zeebe-dmn-worker) and configure it with the same Camunda Cloud API credentials you used for the Showroom repository. Please reference the linked repository for detailed setup instructions.

7) In two separate terminals (one for 'showroom-customer-onboarding' and the other for 'zeebe-dmn-worker'), enter the following command: ***mvn spring-boot:run  -Dmaven.test.skip=true -DskipTests***. During startup, the Showcase will automatically deploy your process model to the configured cluster. 

8) Should you have no errors in your terminals, navigate to [http://localhost:8080/camunda/online/banking/index.html?lang=en](http://localhost:8080/camunda/online/banking/index.html?lang=en) from any browser. You should see the frontend for Camunbankia. Congratulations! Submit an application or two and check out your live processes by launching Operate from your Camunda Cloud SaaS cluster.

## Starting Required Services with Docker

This guide only works for CC SaaS and is also not intended for public deployment.

1) Create a copy of the `./external-task-worker/email-service.env.template` file and remove the `.template` ending. Now, add the credentials to your email service.
2) Create a copy of the `cloud-client-credentials.env.template` file and remove the `.template` ending. Now, add the credentials to your CC cluster.
3) Run `docker-compose up` and wait for the services to build. This might take a while the first time.
4) Once all services are up, you can access the showcase under [localhost:8080/camunda](http://localhost:8080/camunda). You can use `docker-compose up --scale email-worker=4` to scale the email service, if you want to.
5) Once done, run `docker-compose down` to shut down all services.

### Differences to manual guid
* The dmn files are read from [`/src/main/resources/static/bpmn`](./src/main/resources/static/bpmn). In the manual set-up the dmn-worker brings its own dmn file.








