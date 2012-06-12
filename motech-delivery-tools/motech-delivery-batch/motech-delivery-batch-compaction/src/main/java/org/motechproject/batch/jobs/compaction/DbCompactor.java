package org.motechproject.batch.jobs.compaction;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class DbCompactor implements Tasklet, InitializingBean {

    private String masterCouchDbHost;
    private String slaveCouchDbHost;

    @Autowired
    public DbCompactor(@Qualifier("batchCompactionProperties") Properties batchProperties) {
        masterCouchDbHost = batchProperties.getProperty("couchdb.master.host");
        slaveCouchDbHost = batchProperties.getProperty("couchdb.slave.host");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        CouchDbCompactor couchDbCompactor = new CouchDbCompactor();
        couchDbCompactor.compactAllDatabases(masterCouchDbHost);
        couchDbCompactor.compactAllDatabases(slaveCouchDbHost);
        return RepeatStatus.FINISHED;
    }
}
