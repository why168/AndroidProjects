## 14.AndroidTestingStudy

### 介绍

* AndroidJUnitRunner：适用于 Android 且与 JUnit 4 兼容的测试运行器
* Espresso：UI 测试框架；适合应用中的功能性 UI 测试
* UI Automator：UI 测试框架；适合跨系统和已安装应用的跨应用功能性 UI 测试


### 参考资料

* <a target="_blank" href="https://developer.android.com/topic/libraries/testing-support-library/index.html">https://developer.android.com/topic/libraries/testing-support-library/index.html</a>


### 示例代码


```gradle
androidTestCompile "com.android.support:support-annotations:26.0.0-alpha1"
androidTestCompile 'com.android.support.test:runner:0.5'
androidTestCompile 'com.android.support.test:rules:0.5'
androidTestCompile 'com.android.support.test.espresso:espresso-core:2.2.2'
androidTestCompile 'com.android.support.test.uiautomator:uiautomator-v18:2.1.2'
```

```java
/**
 * DemoEspressoTest
 *
 * @author Edwin.Wu
 * @version 2017/8/1 17:38
 * @since JDK1.8
 */
@RunWith(AndroidJUnit4.class)
public class DemoEspressoTest {

    // Rule 这句代表了运行的是哪个Activity
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);
    private UiDevice mDevice;

    private String data;

    @Before
    public void before() {
        mDevice = UiDevice.getInstance(getInstrumentation());
        data = "开始测试，初始化操作";
        Log.e("Edwin", data);
    }

    @Test
    public void DemoTest() {
        onView(withId(R.id.inputField)).perform(typeText("Edwin"), closeSoftKeyboard());
        onView(withId(R.id.buttonLogin)).perform(click());
    }

    @After
    public void after() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
    
```
