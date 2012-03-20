package org.motechproject.deliverytools.document;

import org.junit.Test;
import org.motechproject.deliverytools.document.domain.DocumentClass;
import org.motechproject.deliverytools.document.domain.DocumentMethod;
import org.motechproject.deliverytools.document.presenter.HtmlPresenter;
import org.motechproject.deliverytools.document.presenter.PdfPresenter;

import java.lang.reflect.Method;
import java.util.Arrays;

public class DocGenerationIT {

    @Test
    public void shouldPickAnnotatedClassesAndMakePDF() throws Exception {
        DocumentClass documentClass = new DocumentClass(Integer.class.getName());
        for (Method method : Integer.class.getDeclaredMethods())
            documentClass.addMethod(new DocumentMethod(1, method));

        HtmlPresenter htmlPresenter = new HtmlPresenter(Arrays.asList(documentClass));
        PdfPresenter pdfPresenter = new PdfPresenter();
        pdfPresenter.print(htmlPresenter.print());

    }

}
