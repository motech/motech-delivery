package org.motechproject.deliverytools.document.presenter;

import org.junit.Test;
import org.motechproject.deliverytools.document.domain.DocumentClass;

import java.util.ArrayList;
import java.util.List;

public class HtmlPresenterTest {

    private HtmlPresenter presenter;

    @Test
    public void shouldProduceHTMLString() {
        List<DocumentClass> classList = new ArrayList<DocumentClass>();
        presenter = new HtmlPresenter(classList);
        System.out.println(presenter.show());
    }
}
