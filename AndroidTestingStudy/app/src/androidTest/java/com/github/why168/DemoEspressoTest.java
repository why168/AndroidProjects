package com.github.why168;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

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