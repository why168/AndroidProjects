package github.why168.swipeback.base;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import github.why168.swipeback.R;
import github.why168.swipeback.SwipeBackHelper;
import github.why168.swipeback.utils.StatusBarUtil;

/**
 * 滑动返回Activity基类
 *
 * @author Edwin.Wu
 * @version 2017/2/7 18:05
 * @since JDK1.8
 */
public class SwipeBackActivity extends AppCompatActivity implements SwipeBackHelper.Delegate {
    protected SwipeBackHelper mSwipeBackHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initSwipeBack();
        super.onCreate(savedInstanceState);
    }

    private void initSwipeBack() {
        mSwipeBackHelper = new SwipeBackHelper(this, this);
        // 设置滑动返回是否可用。默认值为 true
        mSwipeBackHelper.setSwipeBackEnable(true);
        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true);
        // 设置是否是微信滑动返回样式。默认值为 true
        mSwipeBackHelper.setIsWeChatStyle(true);
        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
        // 设置是否显示滑动返回的阴影效果。默认值为 true
        mSwipeBackHelper.setIsNeedShowShadow(true);
        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
        mSwipeBackHelper.setIsShadowAlphaGradient(true);

    }

    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    /**
     * 正在滑动返回
     *
     * @param slideOffset 从 0 到 1
     */
    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {

    }

    /**
     * 没达到滑动返回的阈值，取消滑动返回动作，回到默认状态
     */
    @Override
    public void onSwipeBackLayoutCancel() {

    }

    /**
     * 滑动返回执行完毕，销毁当前 Activity
     */
    @Override
    public void onSwipeBackLayoutExecuted() {
        mSwipeBackHelper.swipeBackward();
    }

    /**
     * 返回操作
     */
    @Override
    public void onBackPressed() {
        mSwipeBackHelper.backward();
    }

    /**
     * 设置状态栏颜色
     *
     * @param color number
     */
    protected void setStatusBarColor(@ColorInt int color) {
        setStatusBarColor(color, 0);
    }

    /**
     * 设置状态栏颜色
     *
     * @param color          number
     * @param statusBarAlpha 透明度
     */
    public void setStatusBarColor(@ColorInt int color, @IntRange(from = 0, to = 255) int statusBarAlpha) {
        StatusBarUtil.setColorForSwipeBack(this, color, statusBarAlpha);
    }
}