## 8.HandlerCourse

### 介绍

#####   主要接受子线程发送的数据， 并用此数据配合主线程更新UI。
#####   代码看会理解到，Handler差不多了。

### 参考资料
* <a target="_blank" href="https://developer.android.com/training/multiple-threads/communicate-ui.html#Handler">https://developer.android.com/training/multiple-threads/communicate-ui.html#Handler</a>
* <a target="_blank" href="https://developer.android.com/reference/android/os/Handler.html">https://developer.android.com/reference/android/os/Handler.html</a>
* <a target="_blank" href="http://mp.weixin.qq.com/s/eDjFF-zAr6STaJ7hKhIoDA">http://mp.weixin.qq.com/s/eDjFF-zAr6STaJ7hKhIoDA</a>
* <a target="_blank" href="http://www.cnblogs.com/yw-ah/p/5830458.html">http://www.cnblogs.com/yw-ah/p/5830458.html</a>

<a target="_blank" href=""></a>

### 示例代码

##### 非UI线程更新UI的4种方式来了！

```java
handler.sendEmptyMessage(1);
```

```java
handler.post(new Runnable() {
            @Override
            public void run() {
                textView.setText("update ui");
            }
        });
```

```java
runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText("update ui");
            }
        });
```

```java
textView.post(new Runnable() {
            @Override
            public void run() {
                textView.setText("update ui");
            }
        });

```

##### HandlerThread来了！

```java
private HandlerThread thread;


thread = new HandlerThread("Handler Thread") {
    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        //TODO 子线程操作
        textView.setText("onLooperPrepared");
    }
};
thread.start();

handler = new MyHandler(thread.getLooper(), XActivity.this);
handler.sendEmptyMessage(1);

```

##### Handler弱引用来了！

```java
static class MyHandler extends Handler {
        WeakReference<XActivity> weakReference;

        public MyHandler(XActivity activity) {
            this.weakReference = new WeakReference<XActivity>(activity);
        }

        public MyHandler(Looper looper, XActivity activity) {
            super(looper);
            this.weakReference = new WeakReference<XActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            XActivity XActivity = weakReference.get();
            if (XActivity != null) {
                //TODO 
            }
        }
    }
```


