package com.camunda.demo.customeronboarding.utils;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.gmail.Gmail;

public class GmailUtils {
    
    public static Gmail gmail() {
        // Build a new authorized API client service.
        try {
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            Gmail service = new Gmail.Builder(HTTP_TRANSPORT, GoogleUtils.JSON_FACTORY, GoogleUtils.getCredentials(HTTP_TRANSPORT))
                .setApplicationName(GoogleUtils.APPLICATION_NAME)
                .build();
            return service;
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
