package com.camunda.demo.insuranceapplication.springboot;

import static java.util.Collections.singletonMap;

import static org.glassfish.jersey.servlet.ServletProperties.JAXRS_APPLICATION_CLASS;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.SessionTrackingMode;

import org.camunda.bpm.admin.impl.web.AdminApplication;
import org.camunda.bpm.admin.impl.web.bootstrap.AdminContainerBootstrap;
import org.camunda.bpm.cockpit.impl.web.CockpitApplication;
import org.camunda.bpm.cockpit.impl.web.bootstrap.CockpitContainerBootstrap;
import org.camunda.bpm.engine.rest.filter.CacheControlFilter;
import org.camunda.bpm.spring.boot.starter.webapp.CamundaBpmWebappInitializer;
import org.camunda.bpm.spring.boot.starter.webapp.filter.LazyProcessEnginesFilter;
import org.camunda.bpm.spring.boot.starter.webapp.filter.LazySecurityFilter;
import org.camunda.bpm.tasklist.impl.web.TasklistApplication;
import org.camunda.bpm.tasklist.impl.web.bootstrap.TasklistContainerBootstrap;
import org.camunda.bpm.webapp.impl.engine.EngineRestApplication;
import org.camunda.bpm.webapp.impl.security.auth.AuthenticationFilter;
import org.camunda.bpm.welcome.impl.web.bootstrap.WelcomeContainerBootstrap;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class MyCamundaBpmWebappInitializer extends CamundaBpmWebappInitializer {

  private final Logger logger = LoggerFactory.getLogger(CamundaBpmWebappInitializer.class);

  private static final EnumSet<DispatcherType> DISPATCHER_TYPES = EnumSet.of(DispatcherType.REQUEST);

  private ServletContext servletContext;

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    this.servletContext = servletContext;
    servletContext.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.COOKIE));
    servletContext.addListener(new CockpitContainerBootstrap());
    servletContext.addListener(new AdminContainerBootstrap());
    servletContext.addListener(new WelcomeContainerBootstrap());
    servletContext.addListener(new TasklistContainerBootstrap());

    registerFilter("Authentication Filter", AuthenticationFilter.class, "/camunda/*");

    registerFilter("Security Filter", LazySecurityFilter.class, singletonMap("configFile", "/securityFilterRules.json"), "/*");

    registerFilter("Engines Filter", LazyProcessEnginesFilter.class, "/camunda/app/*");
    registerFilter("CacheControlFilter", CacheControlFilter.class, "/camunda/api/*");

    registerServlet("Cockpit Api", CockpitApplication.class, "/camunda/api/cockpit/*");
    registerServlet("Admin Api", AdminApplication.class, "/camunda/api/admin/*");
    registerServlet("Tasklist Api", TasklistApplication.class, "/camunda/api/tasklist/*");
    registerServlet("Engine Api", EngineRestApplication.class, "/camunda/api/engine/*");
  }

  private FilterRegistration registerFilter(final String filterName, final Class<? extends Filter> filterClass, final String... urlPatterns) {
    return registerFilter(filterName, filterClass, null, urlPatterns);
  }

  private FilterRegistration registerFilter(final String filterName, final Class<? extends Filter> filterClass, final Map<String, String> initParameters,
      final String... urlPatterns) {
    FilterRegistration filterRegistration = servletContext.getFilterRegistration(filterName);

    if (filterRegistration == null) {
      filterRegistration = servletContext.addFilter(filterName, filterClass);
      filterRegistration.addMappingForUrlPatterns(DISPATCHER_TYPES, true, urlPatterns);

      if (initParameters != null) {
        filterRegistration.setInitParameters(initParameters);
      }

      logger.debug("Filter {} for URL {} registered.", filterName, urlPatterns);
    }

    return filterRegistration;
  }

  private ServletRegistration registerServlet(final String servletName, final Class<?> applicationClass, final String... urlPatterns) {
    ServletRegistration servletRegistration = servletContext.getServletRegistration(servletName);

    if (servletRegistration == null) {
      servletRegistration = servletContext.addServlet(servletName, ServletContainer.class);
      servletRegistration.addMapping(urlPatterns);
      servletRegistration.setInitParameters(singletonMap(JAXRS_APPLICATION_CLASS, applicationClass.getName()));

      logger.debug("Servlet {} for URL {} registered.", servletName, urlPatterns);
    }

    return servletRegistration;
  }
}