package org.motechproject.deliverytools.document.presenter;

import org.apache.commons.io.FileUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.motechproject.deliverytools.document.domain.DocumentClass;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Properties;

public class HtmlPresenter {

    private List<DocumentClass> classList;

    public HtmlPresenter(List<DocumentClass> classList) {
        this.classList = classList;
    }

    public String show() throws IOException {
        Properties properties = new Properties();
        properties.setProperty("file.resource.loader.path", getClass().getResource("/templates").getPath());
        VelocityEngine engine = new VelocityEngine(properties);
        engine.init();

        Template template = engine.getTemplate("main_page.vm");
        File cssFile = new File(getClass().getResource("/styles/main.css").getPath());
        String style = FileUtils.readFileToString(cssFile);

        VelocityContext context = new VelocityContext();
        context.put("classList", classList);
        context.put("style", style);

        StringWriter writer = new StringWriter();
        template.merge(context, writer);

        return writer.toString();
    }
}
