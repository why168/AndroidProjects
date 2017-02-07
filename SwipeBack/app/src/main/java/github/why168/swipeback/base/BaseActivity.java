package github.why168.swipeback.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

/**
 * BaseActivity
 *
 * @author Edwin.Wu
 * @version 2017/2/7 18:13
 * @since JDK1.8
 */
public abstract class BaseActivity extends SwipeBackActivity {
    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        bindViews();
        setUpView();
        setUpData(savedInstanceState);
    }

    @LayoutRes
    protected abstract int getLayoutResId();

    protected void bindViews() {
        setContentView(getLayoutResId());
    }

    protected abstract void setUpView();

    protected abstract void setUpData(Bundle savedInstanceState);
}
