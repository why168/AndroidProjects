package github.why168.swipeback.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;

import github.why168.swipeback.R;
import github.why168.swipeback.base.BaseActivity;

/**
 * 带输入框的Activity界面返回
 *
 * @author Edwin.Wu
 * @version 2017/2/7 17:55
 * @since JDK1.8
 */
public class EditTextActivity extends BaseActivity {
    private Toolbar mToolbar;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_edit_text;
    }

    @Override
    protected void setUpView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolbar();
    }


    @Override
    protected void setUpData(Bundle savedInstanceState) {

    }

    private void initToolbar() {
        mToolbar.setBackgroundResource(R.color.colorAccent);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("测试 RecyclerView");
        setStatusBarColor(ContextCompat.getColor(mContext, R.color.read));
        mSwipeBackHelper.setSwipeBackEnable(true);
    }
}
