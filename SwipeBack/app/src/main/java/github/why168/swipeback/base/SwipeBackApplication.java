package github.why168.swipeback.base;

import android.app.Application;

import github.why168.swipeback.SwipeBackManager;

/**
 * BaseApplication
 *
 * @author Edwin.Wu
 * @version 2017/2/7 17:26
 * @since JDK1.8
 */
public class SwipeBackApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SwipeBackManager.getInstance().init(this);
    }
}
