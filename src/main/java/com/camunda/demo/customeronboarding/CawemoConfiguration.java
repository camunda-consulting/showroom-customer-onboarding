package com.camunda.demo.customeronboarding;

import javax.annotation.PostConstruct;

import org.camunda.cawemo.plugin.CawemoEnginePlugin;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "cawemo")
public class CawemoConfiguration {
  
  private final ApplicationContext applicationContext;
  
  @Autowired
  public CawemoConfiguration (ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  private String orgId;
  private String apiKey;
  private String projectName;
  private String url;
  private boolean enabled;
  
  public String getOrgId() {
    return orgId;
  }
  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }
  public String getApiKey() {
    return apiKey;
  }
  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }
  public String getProjectName() {
    return projectName;
  }
  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }
  
  public boolean isEnabled() {
    return enabled;
  }
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }
  
  @PostConstruct
  public void setUpCawemoPlugin() throws BeansException {
    if(enabled) {
      CawemoEnginePlugin plugin = new CawemoEnginePlugin();
      plugin.setOrganizationId(orgId);
      plugin.setProjectName(projectName);
      plugin.setApiKey(apiKey);
      plugin.setCawemoUrl(url);
      plugin.setAuthMode("BASIC");
      plugin.setCustomBasicAuth(false);
      
      ConfigurableApplicationContext configContext = (ConfigurableApplicationContext) applicationContext;
      SingletonBeanRegistry beanRegistry = configContext.getBeanFactory();
      beanRegistry.registerSingleton("cawemoPlugin", plugin);
    } 
  }
  
}
