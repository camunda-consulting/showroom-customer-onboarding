package com.camunda.demo.customeronboarding.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.drive.Drive;
import com.google.api.services.sheets.v4.Sheets;

public class DriveUtils {

    public static Drive drive() {
        // Build a new authorized API client service.
        try {
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            Drive service = new Drive.Builder(HTTP_TRANSPORT, GoogleUtils.JSON_FACTORY, GoogleUtils.getCredentials(HTTP_TRANSPORT))
                .setApplicationName(GoogleUtils.APPLICATION_NAME)
                .build();
            return service;
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Sheets sheets() {
        // Build a new authorized API client service.
        try {
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            Sheets service = new Sheets.Builder(HTTP_TRANSPORT, GoogleUtils.JSON_FACTORY, GoogleUtils.getCredentials(HTTP_TRANSPORT))
                .setApplicationName(GoogleUtils.APPLICATION_NAME)
                .build();
            return service;
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    

    public static File downloadFromDrive(String driveId, String localFileName) throws IOException {
        Drive drive = drive();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        drive.files().get(driveId)
        .executeMediaAndDownloadTo(outputStream);
        File result = new File(localFileName);
        try(FileOutputStream fos = new FileOutputStream(result)) {
            outputStream.writeTo(fos);
        }
        return result;
    }
}