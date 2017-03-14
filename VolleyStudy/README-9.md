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
* 实现底层HttpURLConnection替换成OkHttp

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