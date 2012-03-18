package org.motechproject.deliverytools.document.presenter;

import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class PdfPresenter {

    public static final String MOTECH_API_DOC_PDF = "motech_api_doc.pdf";

    public void print(String htmlInput) throws Exception {
        OutputStream os = new FileOutputStream(MOTECH_API_DOC_PDF);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlInput);
        renderer.layout();
        renderer.createPDF(os);
    }
}
