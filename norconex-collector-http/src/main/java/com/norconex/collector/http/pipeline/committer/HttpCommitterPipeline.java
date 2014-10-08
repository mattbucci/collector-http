/* Copyright 2010-2014 Norconex Inc.
 * 
 * This file is part of Norconex HTTP Collector.
 * 
 * Norconex HTTP Collector is free software: you can redistribute it and/or 
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Norconex HTTP Collector is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Norconex HTTP Collector. If not, 
 * see <http://www.gnu.org/licenses/>.
 */
package com.norconex.collector.http.pipeline.committer;

import com.norconex.collector.core.pipeline.DocumentPipelineContext;
import com.norconex.collector.core.pipeline.committer.CommitModuleStage;
import com.norconex.collector.core.pipeline.committer.DocumentChecksumStage;
import com.norconex.collector.http.crawler.HttpCrawlerEvent;
import com.norconex.collector.http.doc.IHttpDocumentProcessor;
import com.norconex.commons.lang.pipeline.Pipeline;

/**
 * @author Pascal Essiembre
 *
 */
public class HttpCommitterPipeline 
        extends Pipeline<DocumentPipelineContext> {

    public HttpCommitterPipeline() {
        addStage(new DocumentChecksumStage());   
        addStage(new DocumentPostProcessingStage());     
        addStage(new CommitModuleStage());
    }
    
    //--- Document Post-Processing ---------------------------------------------
    private static class DocumentPostProcessingStage 
            extends AbstractCommitterStage {
        @Override
        public boolean executeStage(HttpCommitterPipelineContext ctx) {
            if (ctx.getConfig().getPostImportProcessors() != null) {
                for (IHttpDocumentProcessor postProc :
                        ctx.getConfig().getPostImportProcessors()) {
                    postProc.processDocument(
                            ctx.getHttpClient(), ctx.getDocument());
                    
                    ctx.getCrawler().fireCrawlerEvent(
                            HttpCrawlerEvent.DOCUMENT_POSTIMPORTED, 
                            ctx.getCrawlData(), postProc);
                }            
            }
            return true;
        }
    }  
}
