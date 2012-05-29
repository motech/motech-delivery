package org.motechproject.batch.couchdb.jobs;

import org.motechproject.batch.couchdb.CouchDbReplicator;
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
public class DbReplicator implements Tasklet, InitializingBean {

    private String masterCouchDbHost;
    private String slaveCouchDbHost;

    @Autowired
    public DbReplicator(@Qualifier("batchProperties") Properties batchProperties) {
        masterCouchDbHost = batchProperties.getProperty("couchdb.master.host");
        slaveCouchDbHost = batchProperties.getProperty("couchdb.slave.host");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        CouchDbReplicator couchDbReplicator = new CouchDbReplicator();
        couchDbReplicator.startReplication(masterCouchDbHost, slaveCouchDbHost);
        return RepeatStatus.FINISHED;
    }
}
