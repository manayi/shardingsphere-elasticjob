/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.elasticjob.lite.internal.config;

import io.elasticjob.lite.internal.listener.AbstractJobListener;
import io.elasticjob.lite.internal.listener.AbstractListenerManager;
import io.elasticjob.lite.internal.schedule.JobRegistry;
import io.elasticjob.lite.reg.base.CoordinatorRegistryCenter;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent.Type;

/**
 * 重调度监听管理器.
 */
public final class RescheduleListenerManager extends AbstractListenerManager {
    
    private final ConfigurationNode configNode;
    
    private final String jobName;
    
    public RescheduleListenerManager(final CoordinatorRegistryCenter regCenter, final String jobName) {
        super(regCenter, jobName);
        this.jobName = jobName;
        configNode = new ConfigurationNode(jobName);
    }
    
    @Override
    public void start() {
        addDataListener(new CronSettingAndJobEventChangedJobListener());
    }
    
    class CronSettingAndJobEventChangedJobListener extends AbstractJobListener {
        
        @Override
        protected void dataChanged(final String path, final Type eventType, final String data) {
            if (configNode.isConfigPath(path) && Type.NODE_UPDATED == eventType && !JobRegistry.getInstance().isShutdown(jobName)) {
                JobRegistry.getInstance().getJobScheduleController(jobName).rescheduleJob(LiteJobConfigurationGsonFactory.fromJson(data).getTypeConfig().getCoreConfig().getCron());
            }
        }
    }
}
