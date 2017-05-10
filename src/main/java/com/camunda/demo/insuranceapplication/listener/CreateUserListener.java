package com.camunda.demo.insuranceapplication.listener;

import java.util.List;

import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.authorization.Authorization;
import org.camunda.bpm.engine.authorization.Permissions;
import org.camunda.bpm.engine.authorization.Resources;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.identity.User;

import com.camunda.demo.insuranceapplication.model.Application;

public class CreateUserListener implements ExecutionListener {

  @Override
  public void notify(DelegateExecution execution) throws Exception {
    Application application = (Application) execution.getVariable("application");
    String email = application.getApplicant().getEmail();
    IdentityService identityService = execution.getProcessEngineServices().getIdentityService();
    User user = identityService.newUser(email);
    user.setPassword(email);
    user.setLastName(application.getApplicant().getName());
    List<User> userList = identityService.createUserQuery().userId(email).list();
    if (userList.isEmpty()) {
      identityService.saveUser(user);

      AuthorizationService authorizationService = execution.getProcessEngineServices().getAuthorizationService();
      Authorization taskListAccess = authorizationService.createNewAuthorization(Authorization.AUTH_TYPE_GRANT);
      taskListAccess.setUserId(email);
      taskListAccess.setResource(Resources.APPLICATION);
      taskListAccess.setResourceId("tasklist");
      taskListAccess.addPermission(Permissions.ACCESS);
      authorizationService.saveAuthorization(taskListAccess);
      
      Authorization processInstanceWork = authorizationService.createNewAuthorization(Authorization.AUTH_TYPE_GRANT);
      processInstanceWork.setUserId(email);
      processInstanceWork.setResource(Resources.PROCESS_DEFINITION);
      processInstanceWork.setResourceId("documentRequest");
      processInstanceWork.addPermission(Permissions.UPDATE_INSTANCE);
      processInstanceWork.addPermission(Permissions.READ);
      authorizationService.saveAuthorization(processInstanceWork);
      
    }
  }

}
