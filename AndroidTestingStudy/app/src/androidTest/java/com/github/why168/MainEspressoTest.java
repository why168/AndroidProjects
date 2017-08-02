package com.github.why168;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * @author Edwin.Wu
 * @version 2017/8/1 17:38
 * @since JDK1.8
 */
@RunWith(AndroidJUnit4.class)
public class MainEspressoTest {

    // Rule 这句代表了运行的是哪个Activity
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);
    private UiDevice mDevice;

    // Test 注解代表了我们要运行的测试方法 方法会从上到下依次运行
    @Test
    public void ensureTextChangesWork() {
        // Type text and then press the button.
        onView(withId(R.id.inputField)).perform(typeText("HELLO"), closeSoftKeyboard());
        onView(withId(R.id.buttonLogin)).perform(click());
        // Check that the text was changed.
        onView(withId(R.id.inputField)).check(matches(withText("HELLO")));

    }

    @Test
    public void changeText_newActivity() {
        // Type text and then press the button.
        onView(withId(R.id.inputField)).perform(typeText("NewText"), closeSoftKeyboard());
        onView(withId(R.id.switchActivity)).perform(click());

        // 这里可以发现Test只是找UI
        onView(withId(R.id.resultView)).check(matches(withText("NewText")));
    }

    @Test
    public void d() throws UiObjectNotFoundException {
        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(getInstrumentation());

        // Perform a short press on the HOME button
        mDevice.pressHome();

        // Bring up the default launcher by searching for
        // a UI component that matches the content-description for the launcher button
        UiObject allAppsButton = mDevice.findObject(new UiSelector().description("Apps"));

        // Perform a click on the button to bring up the launcher
        allAppsButton.clickAndWaitForNewWindow();
    }
}