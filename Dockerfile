FROM docker.consulting.camunda.com/customized-wildfly

# remove default webapp
RUN rm -rf /camunda/standalone/deployments/camunda-example-invoice-*.war
#ADD ~/.camunda/ /root/

# add showcase webapp
ADD target/camunda-showcase-insurance-application.war /camunda/standalone/deployments/

EXPOSE 8787

CMD ["/camunda/bin/standalone.sh","-b","0.0.0.0","-bmanagement","0.0.0.0","--debug"]
