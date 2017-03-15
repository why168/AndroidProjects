## 9.VolleyStudy

### 介绍

* Android系统中主要提供了两种方式来进行HTTP通信，HttpURLConnection和HttpClient
* Android 4.4以上使用HttpURLConnection底层使用OkHttp实现
* Volley自带Request
	* StringRequest
	* ImageRequest
	* JsonObjectRequest
	* ClearCacheRequest
* 对Volley扩展的Request
	* JsonArrayRequest
	* MultipartRequest
	* GsonRequest
* HttpStack
    * HurlStack
    * HttpClientStack
    * OkApacheClientStack
    * OkHttpURLConnectionStack

* 实现底层HttpURLConnection替换成OkHttp

* TrafficStats——流量统计类的范例，获取实时网速
	* static long getMobileRxBytes() //获取通过Mobile连接收到的字节总数，不包含WiFi
	* static long getMobileRxPackets() //获取Mobile连接收到的数据包总数
	* static long getMobileTxPackets() //Mobile发送的总数据包数
	* static long getTotalRxBytes() //获取总的接受字节数，包含Mobile和WiFi等
	* static long getTotalRxPackets() //总的接受数据包数，包含Mobile和WiFi等
	* static long getTotalTxBytes() //总的发送字节数，包含Mobile和WiFi等
	* static long getTotalTxPackets() //发送的总数据包数，包含Mobile和WiFi等
	* static long getUidRxBytes(int uid) //获取某个网络UID的接受字节数，某一个进程的总接收量
	* static long getUidTxBytes(int uid) //获取某个网络UID的发送字节数，某一个进程的总发送量

* Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);//设置线程优先级为后台，这样当多个线程并发后很多无关紧要的线程分配的CPU时间将会减少，有利于主线程的处理，有以下几种:
	* int THREAD_PRIORITY_AUDIO //标准音乐播放使用的线程优先级
	* int THREAD_PRIORITY_BACKGROUND //标准后台程序
	* int THREAD_PRIORITY_DEFAULT // 默认应用的优先级
	* int THREAD_PRIORITY_DISPLAY //标准显示系统优先级，主要是改善UI的刷新
	* int THREAD_PRIORITY_FOREGROUND //标准前台线程优先级
	* int THREAD_PRIORITY_LESS_FAVORABLE //低于favorable
	* int THREAD_PRIORITY_LOWEST //有效的线程最低的优先级
	* int THREAD_PRIORITY_MORE_FAVORABLE //高于favorable
	* int THREAD_PRIORITY_URGENT_AUDIO //标准较重要音频播放优先级
	* int THREAD_PRIORITY_URGENT_DISPLAY //标准较重要显示优先级，对于输入事件同样适用。

### 参考资料
* <a target="_blank" href="https://developer.android.com/training/volley/index.html">https://developer.android.com/training/volley/index.html</a>
* <a target="_blank" href="https://android-developers.googleblog.com/2011/09/androids-http-clients.html">https://android-developers.googleblog.com/2011/09/androids-http-clients.html</a>

### 示例代码



```gradle
compile 'com.squareup.okhttp3:okhttp-urlconnection:3.6.0'
compile 'com.squareup.okhttp3:okhttp-apache:3.6.0'
compile 'com.squareup.okhttp3:okhttp:3.6.0'
compile 'com.squareup.okhttp3:logging-interceptor:3.6.0'
compile 'com.google.code.gson:gson:2.8.0'
```

```java
/**
     * 请求方式一：
     * <p>
     * volley默认的请求方式
     * 2.3以后底层用的是HttpURLConnection
     */
    void HttpRequest$1() {
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(initializedRequest());
    }

    /**
     * 请求方式二：
     * 底层使用okHttp-apache
     */
    void HttpRequest$2() {
        OkApacheClient okApacheClient = new OkApacheClient();
        OkApacheClientStack clientStack = new OkApacheClientStack(okApacheClient);

        RequestQueue queue = Volley.newRequestQueue(this, clientStack);
        queue.add(initializedRequest());
    }

    /**
     * 请求方式三：
     * 底层使用okHttp-urlConnection
     */
    void HttpRequest$3() {
        OkHttpURLConnectionStack clientStack = new OkHttpURLConnectionStack();

        RequestQueue queue = Volley.newRequestQueue(this, clientStack);
        queue.add(initializedRequest());
    }

    Request initializedRequest() {
        return new StringRequest(Request.Method.GET,
                "http://www.aybrowser.com/sdk/partners/",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Edwin", "response = " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Edwin", "VolleyError = " + error);
                    }
                });
    }

    /**
     * post请求
     * 多类型上传
     * 文件+字符
     */
    void MultipartRequest$4() {
        try {
            //构造参数列表
            List<Part> partList = new ArrayList<Part>();
            partList.add(new StringPart("username", "edwin"));
            partList.add(new StringPart("email", "edwin.wu@gmail.com"));

            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/ic_launcher.png";
            partList.add(new FilePart("ic_launcher", new File(path)));

            //获取队列
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String url = "http://www.cnblogs.com/";
            //生成请求
            MultipartRequest profileUpdateRequest = new MultipartRequest(url,
                    partList.toArray(new Part[partList.size()]),
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("Edwin", "onResponse : " + response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Edwin", "MultipartRequest : " + error.getMessage(), error);
                        }
                    }) {
                @Override
                public String getBodyContentType() {
                    return super.getBodyContentType();
                }

            };
            requestQueue.add(profileUpdateRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
```
