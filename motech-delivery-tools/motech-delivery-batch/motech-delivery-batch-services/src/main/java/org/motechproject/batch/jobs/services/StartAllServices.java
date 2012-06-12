package org.motechproject.batch.jobs.services;

import org.motechproject.batch.utils.AntTask;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Properties;

@Component
public class StartAllServices implements Tasklet, InitializingBean {

    private Properties batchProperties;

    @Autowired
    public StartAllServices(@Qualifier("batchServiceProperties") Properties batchProperties) {
        this.batchProperties = batchProperties;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        String buildFilePath = batchProperties.getProperty("deploy.build.file");
        String buildFile = buildFilePath + File.separator + "operate.xml";
        String environment = batchProperties.getProperty("environment");
        String antFile = batchProperties.getProperty("ant.file");
        int returnCode = new AntTask(buildFile, buildFilePath, environment, antFile).run("start.services");
        if(returnCode == 0)
            return RepeatStatus.FINISHED;
        else
            throw new Exception("Return code from ant task is : " + returnCode + ". See the server logs for more details.");
    }
}
