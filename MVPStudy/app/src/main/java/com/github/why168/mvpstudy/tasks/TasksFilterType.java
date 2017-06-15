/*
 * Copyright 2016, The Android Open Source Project
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

package com.github.why168.mvpstudy.tasks;

/**
 * 使用的滤光片转轮在任务列表中。
 */
public enum TasksFilterType {
    /**
     * 不过滤任务。
     */
    ALL_TASKS,

    /**
     * 过滤器只活跃(未完成)的任务。
     */
    ACTIVE_TASKS,

    /**
     * 过滤器只完成任务。
     */
    COMPLETED_TASKS
}
