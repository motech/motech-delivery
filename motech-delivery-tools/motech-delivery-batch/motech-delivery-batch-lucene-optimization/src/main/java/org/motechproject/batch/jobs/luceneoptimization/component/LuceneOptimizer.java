package org.motechproject.batch.jobs.luceneoptimization.component;

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
public class LuceneOptimizer implements Tasklet, InitializingBean {

    private String luceneHost;
    private String couchdDbPort;

    @Autowired
    public LuceneOptimizer(@Qualifier("luceneOptimizationProperties") Properties couchLuceneProperties) {
        luceneHost = couchLuceneProperties.getProperty("couchdb.lucene.host");
        couchdDbPort = couchLuceneProperties.getProperty("couchdb.port");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        CouchDbLuceneOptimizer couchDbLuceneOptimizer = new CouchDbLuceneOptimizer();
        couchDbLuceneOptimizer.optimize(luceneHost, couchdDbPort);
        return RepeatStatus.FINISHED;
    }
}
