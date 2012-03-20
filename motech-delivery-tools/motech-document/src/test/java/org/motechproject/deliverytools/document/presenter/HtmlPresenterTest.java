package org.motechproject.deliverytools.document.presenter;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.motechproject.deliverytools.document.domain.DocumentClass;
import org.motechproject.deliverytools.document.domain.DocumentMethod;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class HtmlPresenterTest {

    private HtmlPresenter presenter;

    @Test
    public void shouldProduceHTMLString() throws IOException {
        DocumentClass documentClass = new DocumentClass(Integer.class.getName());

        for (Method method : Integer.class.getDeclaredMethods())
            documentClass.addMethod(new DocumentMethod(1, method));

        presenter = new HtmlPresenter(Arrays.asList(documentClass));
        FileUtils.writeStringToFile(new File("sample_doc.html"), presenter.print());
    }
}
