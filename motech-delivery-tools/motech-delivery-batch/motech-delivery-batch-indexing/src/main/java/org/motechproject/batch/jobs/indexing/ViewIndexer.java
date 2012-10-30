/*
 * Copyright 2009-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.motechproject.batch.jobs.indexing;

import org.apache.commons.lang.StringUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * {@link ItemReader} with hard-coded input data.
 */
@Component
public class ViewIndexer implements Tasklet, InitializingBean {

    private String masterCouchDbHost;
    private String slaveCouchDbHost;

    @Autowired
    public ViewIndexer(@Qualifier("batchIndexingProperties") Properties batchProperties) {
        masterCouchDbHost = batchProperties.getProperty("couchdb.master.host");
        slaveCouchDbHost = batchProperties.getProperty("couchdb.slave.host");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }

    @Override
    public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
        CouchDBViewIndexer couchDBViewIndexer = new CouchDBViewIndexer();
        if (StringUtils.isNotBlank(masterCouchDbHost))
            couchDBViewIndexer.indexAllViews(masterCouchDbHost);
        if (StringUtils.isNotBlank(slaveCouchDbHost))
            couchDBViewIndexer.indexAllViews(slaveCouchDbHost);
        return RepeatStatus.FINISHED;
    }
}
