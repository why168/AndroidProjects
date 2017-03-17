## 11.PermissionManage

### 介绍


* 如果设备运行的是 Android 6.0（API 级别 23）或更高版本，并且应用的 targetSdkVersion 是 23 或更高版本，则应用在运行时向用户请求权限。
* 如果设备运行的是 Android 5.1（API 级别 22）或更低版本，并且应用的 targetSdkVersion 是 22 或更低版本，则系统会在用户安装应用时要求用户授予权限。

### 参考资料
* <a target="_blank" href="https://developer.android.google.cn/guide/topics/security/permissions.html">https://developer.android.google.cn/guide/topics/security/permissions.html</a>
* 以下是需要单独申请的权限,共分为9组,每组只要有一个权限申请成功了,就默认整组权限都可以使用了

```xml
group:android.permission-group.CONTACTS
    permission:android.permission.WRITE_CONTACTS
    permission:android.permission.GET_ACCOUNTS    
    permission:android.permission.READ_CONTACTS

  group:android.permission-group.PHONE
    permission:android.permission.READ_CALL_LOG
    permission:android.permission.READ_PHONE_STATE 
    permission:android.permission.CALL_PHONE
    permission:android.permission.WRITE_CALL_LOG
    permission:android.permission.USE_SIP
    permission:android.permission.PROCESS_OUTGOING_CALLS
    permission:com.android.voicemail.permission.ADD_VOICEMAIL

  group:android.permission-group.CALENDAR
    permission:android.permission.READ_CALENDAR
    permission:android.permission.WRITE_CALENDAR

  group:android.permission-group.CAMERA
    permission:android.permission.CAMERA

  group:android.permission-group.SENSORS
    permission:android.permission.BODY_SENSORS

  group:android.permission-group.LOCATION
    permission:android.permission.ACCESS_FINE_LOCATION
    permission:android.permission.ACCESS_COARSE_LOCATION

  group:android.permission-group.STORAGE
    permission:android.permission.READ_EXTERNAL_STORAGE
    permission:android.permission.WRITE_EXTERNAL_STORAGE

  group:android.permission-group.MICROPHONE
    permission:android.permission.RECORD_AUDIO

  group:android.permission-group.SMS
    permission:android.permission.READ_SMS
    permission:android.permission.RECEIVE_WAP_PUSH
    permission:android.permission.RECEIVE_MMS
    permission:android.permission.RECEIVE_SMS
    permission:android.permission.SEND_SMS
    permission:android.permission.READ_CELL_BROADCASTS
```

* 以下是普通权限,只需要在AndroidManifest.xml中申请即可

```
  android.permission.ACCESS_LOCATION_EXTRA_COMMANDS
  android.permission.ACCESS_NETWORK_STATE
  android.permission.ACCESS_NOTIFICATION_POLICY
  android.permission.ACCESS_WIFI_STATE
  android.permission.ACCESS_WIMAX_STATE
  android.permission.BLUETOOTH
  android.permission.BLUETOOTH_ADMIN
  android.permission.BROADCAST_STICKY
  android.permission.CHANGE_NETWORK_STATE
  android.permission.CHANGE_WIFI_MULTICAST_STATE
  android.permission.CHANGE_WIFI_STATE
  android.permission.CHANGE_WIMAX_STATE
  android.permission.DISABLE_KEYGUARD
  android.permission.EXPAND_STATUS_BAR
  android.permission.FLASHLIGHT
  android.permission.GET_ACCOUNTS
  android.permission.GET_PACKAGE_SIZE
  android.permission.INTERNET
  android.permission.KILL_BACKGROUND_PROCESSES
  android.permission.MODIFY_AUDIO_SETTINGS
  android.permission.NFC
  android.permission.READ_SYNC_SETTINGS
  android.permission.READ_SYNC_STATS
  android.permission.RECEIVE_BOOT_COMPLETED
  android.permission.REORDER_TASKS
  android.permission.REQUEST_INSTALL_PACKAGES
  android.permission.SET_TIME_ZONE
  android.permission.SET_WALLPAPER
  android.permission.SET_WALLPAPER_HINTS
  android.permission.SUBSCRIBED_FEEDS_READ
  android.permission.TRANSMIT_IR
  android.permission.USE_FINGERPRINT
  android.permission.VIBRATE
  android.permission.WAKE_LOCK
  android.permission.WRITE_SYNC_SETTINGS
  com.android.alarm.permission.SET_ALARM
  com.android.launcher.permission.INSTALL_SHORTCUT
  com.android.launcher.permission.UNINSTALL_SHORTCUT
```

### 示例代码


```java
/**
 * BaseActivity
 * 
 * @author Edwin.Wu
 * @version 2017/3/17$ 14:12$
 * @since JDK1.8
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 为子类提供一个权限检查方法
     *
     * @param permissions
     * @return
     */
    public boolean hasPermission(String... permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 权限请求申请
     *
     * @param requestCode 请求码
     * @param permissions 权限
     */
    public void requestPermission(int requestCode, String... permissions) {
        ActivityCompat.requestPermissions(this, permissions, requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PermissionsConstans.WRITE_STORAGE_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    doSDCardPermission();
                } else {
                    //TODO 提示用户权限未授予
                    Toast.makeText(BaseActivity.this, "WRITE_EXTERNAL_STORAGE 权限未开启", Toast.LENGTH_SHORT).show();
                }
                break;
            case PermissionsConstans.CALL_PHONE_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    doCallPhone();
                } else {
                    //TODO 提示用户权限未授予
                    Toast.makeText(BaseActivity.this, "ACTION_CALL 权限未开启", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }


    /**
     * 默认的写SD权限处理
     */
    protected void doSDCardPermission() {
        //TODO
    }

    /**
     * 默认的打电话处理
     */
    protected void doCallPhone() {
        //TODO 
    }
}

```

<br/>

```java
/**
 * BaseActivity
 *
 * @author Edwin.Wu
 * @version 2017/3/17 11:59
 * @since JDK1.8
 */
public class PermissionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
    }

    public void callPhone(View view) {
        if (hasPermission(android.Manifest.permission.CALL_PHONE)) {
            doCallPhone();
        } else {
            requestPermission(PermissionsConstans.CALL_PHONE_CODE, android.Manifest.permission.CALL_PHONE);
        }
    }

    public void sdCardPermission(View view) {
        if (hasPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            doSDCardPermission();
        } else {
            requestPermission(PermissionsConstans.WRITE_STORAGE_CODE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }


    @Override
    protected void doCallPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + "10000"));
        startActivity(intent);
    }

    @Override
    protected void doSDCardPermission() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request
                .Builder()
                .url("http://img.mmjpg.com/2015/350/3.jpg")
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();
                InputStream is = body.byteStream();
                int len = 0;
                byte[] bytes = new byte[2048];
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg";
                File file = new File(path);
                FileOutputStream fos = new FileOutputStream(file);
                while ((len = is.read(bytes)) != -1) {
                    fos.write(bytes, 0, len);
                    fos.flush();
                }

                Toast.makeText(PermissionActivity.this, "SDCard写入成功", Toast.LENGTH_SHORT).show();
                Log.e("Edwin", "SDCard写入成功");
            }
        });


    }

}
```
<br/>

```
/**
 * PermissionGen框架
 *
 * @author Edwin.Wu
 * @version 2017/3/17 23:19
 * @since JDK1.8
 */
public class PermissionGenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_gen);
    }

    public void openCamera(View view) {
        PermissionGen.needPermission(this, 200, Manifest.permission.CAMERA);
    }

    public void openGroup(View view) {
        PermissionGen
                .with(this)
                .addRequestCode(100)
                .permissions(
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.RECEIVE_SMS,
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_CONTACTS)
                .request();
    }


    @PermissionSuccess(requestCode = 100)
    public void openGroupSuccess() {
        Toast.makeText(this, "Group Permission Success", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, ContactActivity.class));
    }

    @PermissionFail(requestCode = 100)
    private void openGroupFail() {
        Toast.makeText(this, "Group permission is not granted", Toast.LENGTH_SHORT).show();
    }

    @PermissionSuccess(requestCode = 200)
    public void openCameraSuccess() {
        Toast.makeText(this, "Camera permission Success", Toast.LENGTH_SHORT).show();
    }

    @PermissionFail(requestCode = 200)
    public void openCameraFail() {
        Toast.makeText(this, "Camera permission is not granted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }
}
```