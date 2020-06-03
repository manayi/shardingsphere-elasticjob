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

package io.elasticjob.lite.lifecycle.api;

import io.elasticjob.lite.lifecycle.domain.ShardingInfo;

import java.util.Collection;

/**
 * 作业分片状态展示的API.
 */
public interface ShardingStatisticsAPI {
    
    /**
     * 获取作业分片信息集合.
     *
     * @param jobName 作业名称
     * @return 作业分片信息集合
     */
    Collection<ShardingInfo> getShardingInfo(String jobName);
}
