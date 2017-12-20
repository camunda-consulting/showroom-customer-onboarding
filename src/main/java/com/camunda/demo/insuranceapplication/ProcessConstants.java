package com.camunda.demo.insuranceapplication;

public class ProcessConstants {

  public static final String PROCESS_KEY_insurance_application_en = "insurance_application_en";
  public static final String PROCESS_KEY_insurance_application_de = "insurance_application_de";

  public static final String PROCESS_KEY_requestDocument_en = "requestDocument_en";
  public static final String PROCESS_KEY_requestDocument_de = "requestDocument_de";

  public static final String DECISION_KEY_checkRisk_en = "checkRisk_en";
  public static final String DECISION_KEY_checkRisk_de = "checkRisk_de";

  /*
   * Variables of main process.
   */
  public static final String MESSAGE_documentRequested = "Message_DocumentRequested";
  public static final String VAR_NAME_uiBaseUrl = "uiBaseUrl";
  public static final String VAR_NAME_application = "application";
  public static final String VAR_NAME_risks = "risks";
  public static final String VAR_NAME_approved = "approved";
  public static final String VAR_NAME_riskLevel = "riskLevel";
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

  public static final String VERSION_TAG_old_insurace_application = "oldInsuranceApplication";
}
