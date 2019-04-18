FROM docker.consulting.camunda.com/customized-wildfly

# remove default example
RUN rm -rf /camunda/standalone/deployments/camunda-example-invoice-*.war
#ADD ~/.camunda/ /root/

# add showcase
ADD target/camunda-showcase-customer-onboarding.war /camunda/standalone/deployments/

# increase default session timeout to 8h and deployment timeout to 15min
RUN /camunda/bin/jboss-cli.sh --commands="embed-server, \
	/subsystem=undertow/servlet-container=default:write-attribute(name=default-session-timeout, value=480), \
	/system-property=jboss.as.management.blocking.timeout:add(value=900), \
	/subsystem=deployment-scanner/scanner=default:write-attribute(name=deployment-timeout,value=900)"

EXPOSE 8787

CMD ["/camunda/bin/standalone.sh","-b","0.0.0.0","-bmanagement","0.0.0.0","--debug"]