/*
 * Copyright (C) 2011 The Android Open Source Project
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

package com.github.why168.volley;

/**
 * An interface for performing requests.
 * 一个接口来执行请求。
 */
public interface Network {
    /**
     * Performs the specified request.
     * 执行指定的请求。
     *
     * @param request Request to process 请求处理
     * @return A {@link NetworkResponse} with data and caching metadata; will never be null 数据和缓存元数据;永远不会空
     * @throws VolleyError on errors
     */
    public NetworkResponse performRequest(Request<?> request) throws VolleyError;
}
