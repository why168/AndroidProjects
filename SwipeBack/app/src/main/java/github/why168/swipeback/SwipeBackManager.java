package github.why168.swipeback;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.View;

import java.util.Stack;

/**
 * SwipeBackManager
 *
 * @author Edwin.Wu
 * @version 2017/2/7 17:54
 * @since JDK1.8
 */
public class SwipeBackManager implements Application.ActivityLifecycleCallbacks {
    private static final SwipeBackManager sInstance = new SwipeBackManager();
    private Stack<Activity> mActivityStack = new Stack<>();

    public static SwipeBackManager getInstance() {
        return sInstance;
    }

    private SwipeBackManager() {
    }

    /**
     * 必须在 Application 的 onCreate 方法中调用
     */
    public void init(Application application) {
        application.registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        mActivityStack.add(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        mActivityStack.remove(activity);
    }

    /**
     * 获取倒数第二个 Activity
     *
     * @return Activity
     */
    @Nullable
    public Activity getPenultimateActivity() {
        Activity activity = null;
        try {
            if (mActivityStack.size() > 1) {
                activity = mActivityStack.get(mActivityStack.size() - 2);
            }
        } catch (Exception e) {
        }
        return activity;
    }

    public static void onPanelSlide(float slideOffset) {
        try {
            Activity activity = getInstance().getPenultimateActivity();
            if (activity != null) {
                View decorView = activity.getWindow().getDecorView();
                ViewCompat.setTranslationX(decorView, -(decorView.getMeasuredWidth() / 3.0f) * (1 - slideOffset));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onPanelClosed() {
        try {
            Activity activity = getInstance().getPenultimateActivity();
            if (activity != null) {
                View decorView = activity.getWindow().getDecorView();
                ViewCompat.setTranslationX(decorView, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}