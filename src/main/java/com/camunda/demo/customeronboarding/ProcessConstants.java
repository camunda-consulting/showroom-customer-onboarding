package com.camunda.demo.customeronboarding;

public class ProcessConstants {
  
  private static final String BASIC_FILEPATH_ = "static/bpmn/";
  
  public static final String FILEPATH_CUSTOMER_ONBOARDING_EN = BASIC_FILEPATH_ + "customer_onboarding_en.bpmn"; 
  public static final String FILEPATH_CUSTOMER_ONBOARDING_DE = BASIC_FILEPATH_ + "customer_onboarding_de.bpmn"; 
  
  public static final String FILEPATH_CUSTOMER_ONBOARDING_EN_OLD = BASIC_FILEPATH_ + "customer_onboarding_en_old.bpmn"; 
  public static final String FILEPATH_CUSTOMER_ONBOARDING_DE_OLD = BASIC_FILEPATH_ + "customer_onboarding_de_old.bpmn"; 
  
  public static final String FILEPATH_DOCUMENT_REQUEST_EN = BASIC_FILEPATH_ + "document_request_en.bpmn"; 
  public static final String FILEPATH_DOCUMENT_REQUEST_DE = BASIC_FILEPATH_ + "document_request_de.bpmn"; 
  
  public static final String FILEPATH_RISK_CHECK_EN = BASIC_FILEPATH_ + "risk_check_en.dmn"; 
  public static final String FILEPATH_RISK_CHECK_DE = BASIC_FILEPATH_ + "risk_check_de.dmn"; 

  public static final String PROCESS_KEY_customer_onboarding_en = "customer_onboarding_en";
  public static final String PROCESS_KEY_customer_onboarding_de = "customer_onboarding_de";

  public static final String PROCESS_KEY_requestDocument_en = "requestDocument_en";
  public static final String PROCESS_KEY_requestDocument_de = "requestDocument_de";

  public static final String DECISION_KEY_checkRisk_en = "checkRisk_en";
  public static final String DECISION_KEY_checkRisk_de = "checkRisk_de";

  public static final String DEPLOYMENT_NAME = "Most_Important_Deployment";
  
  /*
   * Variables of main process.
   */
  public static final String MESSAGE_documentRequested = "Message_DocumentRequested";
  public static final String VAR_NAME_uiBaseUrl = "uiBaseUrl";
  public static final String VAR_NAME_application = "application";
  public static final String VAR_NAME_risks = "risks";
  public static final String VAR_NAME_score = "score";
  public static final String VAR_NAME_approved = "approved";
  public static final String VAR_NAME_riskLevel = "riskLevel";
  public static final String VAR_NAME_applicationNumber = "applicationNumber";
  public static final String VAR_NAME_applicantName = "applicantName";
  /** Id for the next requested document **/
  public static final String VAR_NAME_documentReferenceId = "documentReferenceId";
  public static final String VAR_NAME_requestedDocumentDescription = "requestedDocumentDescription";
  public static final String VAR_NAME_PREFIX_documentFile = "document_";
  /** Contains a JSON map "reference id"->"description" */
  public static final String VAR_NAME_documents = "documents";

  /*
   * Variables of document request sub-process.
   */
  public static final String MESSAGE_documentReceived = "MESSAGE_documentReceived";
  public static final String VAR_NAME_document = "document";

  public static final String VERSION_TAG_old_insurace_application = "oldCustomerOnboarding";
}
