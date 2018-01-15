## 10.OkHttpStudy

### 介绍

* 由Square公司贡献的一个安卓端处理网络请求的开源框架
* 优势:
	1. 允许连接到同一个主机地址的所有请求,提高请求效率
	2. 共享Socket,减少对服务器的请求次数
	3. 通过连接池,减少了请求延迟
	4. 缓存响应数据来减少重复的网络请求
	5. 减少了对数据流量的消耗
	6. 自动处理GZip压缩
* 功能:
	1. Get请求(同步和异步)
	2. POST请求表单(key-value)提交(JSON/String/File/...)
	4. 文件上传下载

### 示例代码

```java

/**
 * SimpleOkHttp
 *
 * @author Edwin.Wu
 * @version 2017/3/16$ 10:40$
 * @since JDK1.8
 */
class SimpleOkHttp {
    private static Dispatcher dispatcher;
    private final static OkHttpClient client = new OkHttpClient
            .Builder()
            .dispatcher(dispatcher = new Dispatcher())
            .readTimeout(500, TimeUnit.MILLISECONDS)
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    private final static Request request = new Request.Builder()
            .url("http://www.baidu.com")
            .header("User-Agent", "OkHttp Headers.java")
            .build();

    /**
     * Caused by: android.os.NetworkOnMainThreadException
     * 同步执行
     */
    static void okHttp$1() {
        try {
            Response response = client.newCall(request).execute();
            System.out.println("Result: " + response.isSuccessful());
            System.out.println("Server: " + response.header("Server"));
            System.out.println("ResponseBody: " + response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步执行
     */
    static void okHttp$2() {
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Exception: " + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("Result: " + response.isSuccessful());
                System.out.println("Server: " + response.header("Server"));
                System.out.println("ResponseBody: " + response.body().string());
            }
        });
    }

    static void okHttp$CancelAll() {
        client.dispatcher().cancelAll();
    }
}
    
```
