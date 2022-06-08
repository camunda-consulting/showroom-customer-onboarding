package com.camunda.demo.customeronboarding.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;

public class DocxPdfUtils {

    public static void generateDocx(String templateFileName, String targetFileName, Map<String, Object> variables)
            throws IOException, XDocReportException {
        InputStream in = DocxPdfUtils.class.getClassLoader().getResourceAsStream(templateFileName);

        IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);

        IContext context = report.createContext();
        for (Map.Entry<String, Object> entry : variables.entrySet()) {
            context.put(entry.getKey(), entry.getValue());
        }

        OutputStream outDocx = new FileOutputStream(new File("Meeting notesOut.docx"));
        report.process(context, outDocx);
    }

    public static void generatePdf(String templateFileName, String targetFileName, Map<String, Object> variables)
            throws IOException, XDocReportException {

        InputStream in = DocxPdfUtils.class.getClassLoader().getResourceAsStream(templateFileName);

        IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);

        IContext context = report.createContext();
        for (Map.Entry<String, Object> entry : variables.entrySet()) {
            context.put(entry.getKey(), entry.getValue());
        }

        OutputStream out = new FileOutputStream(new File(targetFileName));

        Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
        report.convert(context, options, out);
    }

    private DocxPdfUtils() {
    }
}
