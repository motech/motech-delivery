package org.motechproject.deliverytools.document.presenter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.motechproject.deliverytools.document.domain.DocumentClass;

import java.io.StringWriter;
import java.util.List;
import java.util.Properties;

public class HtmlPresenter {

    private List<DocumentClass> classList;

    public HtmlPresenter(List<DocumentClass> classList) {
        this.classList = classList;
    }

    public String show() {
        Properties properties = new Properties();
        properties.setProperty("file.resource.loader.path", getClass().getResource("/templates").getPath());

        VelocityEngine ve = new VelocityEngine(properties);
        ve.init();
        VelocityContext context = new VelocityContext();
        context.put("key", "hh");

        Template template = ve.getTemplate("main_page.vm");
        StringWriter writer = new StringWriter();
        template.merge(context, writer);

        return writer.toString();
    }
}
