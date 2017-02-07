package github.why168.swipeback.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import github.why168.swipeback.R;
import github.why168.swipeback.base.BaseActivity;

/**
 * MainActivity
 *
 * @author Edwin.Wu
 * @version 2017/2/7 17:30
 * @since JDK1.8
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {
    private Button button_edit_text_activity;
    private Button button_view_pager_activity;

    /**
     * 重写isSupportSwipeBack是否开启滑动返回，默认开启
     *
     * @return false关闭，true支持
     */
    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setUpView() {
        button_edit_text_activity = (Button) findViewById(R.id.button_edit_text_activity);
        button_view_pager_activity = (Button) findViewById(R.id.button_view_pager_activity);
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        button_edit_text_activity.setOnClickListener(this);
        button_view_pager_activity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_edit_text_activity:
                mSwipeBackHelper.forward(EditTextActivity.class);
                break;
            case R.id.button_view_pager_activity:
                mSwipeBackHelper.forward(ViewPagerActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
