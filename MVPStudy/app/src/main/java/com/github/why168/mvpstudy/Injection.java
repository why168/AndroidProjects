/*
 * Copyright (C) 2015 The Android Open Source Project
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

package com.github.why168.mvpstudy;

import android.content.Context;
import android.support.annotation.NonNull;

import com.github.why168.mvpstudy.data.FakeTasksRemoteDataSource;
import com.github.why168.mvpstudy.data.source.TasksRepository;
import com.github.why168.mvpstudy.data.source.local.TasksLocalDataSource;
import com.github.why168.mvpstudy.tasks.TasksDBPresenter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 注射
 *
 * @author Edwin.Wu
 * @version 2017/6/1 19:42
 * @since JDK1.8
 */
public class Injection {

    /**
     * 提供任务存储库
     *
     * @param context context
     * @return TasksRepository
     */
    public static TasksRepository provideTasksRepository(@NonNull Context context) {
        FakeTasksRemoteDataSource fakeTasksRemoteDataSource = FakeTasksRemoteDataSource.getInstance();
        TasksLocalDataSource tasksLocalDataSource = TasksLocalDataSource.getInstance(checkNotNull(context));
        return TasksRepository.getInstance(fakeTasksRemoteDataSource, tasksLocalDataSource);
    }


}
