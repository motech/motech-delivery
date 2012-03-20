package org.motechproject.deliverytools.document.presenter;

import org.junit.Before;
import org.junit.Test;

public class PdfPresenterTest {

    private PdfPresenter pdfPresenter;

    @Before
    public void setUp(){
        pdfPresenter = new PdfPresenter();
    }

    @Test
    public void shouldMakeAPdfFileFromHtml() throws Exception {
        String html = "<html><body><p>hi there</p></body></html>";
        pdfPresenter.print(html);
    }
}