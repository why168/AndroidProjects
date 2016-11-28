package com.github.why168.databinding.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.github.why168.databinding.R;
import com.github.why168.databinding.base.BaseActivity;
import com.github.why168.databinding.databinding.ActivityMainBinding;
import com.github.why168.databinding.model.Person;
import com.github.why168.databinding.model.User;

/**
 * DataBinding
 *
 * @author Edwin.Wu
 * @version 2016/11/25 16:15
 * @see <a href="https://developer.android.com/topic/libraries/data-binding/index.html">https://developer.android.com/topic/libraries/data-binding/index.html</a>
 * @since JDK1.8
 */
public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setUpData() {
        b.setPerson(new Person("Edwin", 25, "男", "女", "Android开发工程师", "北京海淀区"));
        b.setPresenter(new Presenter());
    }

    public class Presenter {
        public void updatePerson(View v, String name) {
            b.setPerson(new Person("Abby", 22, "女", "男", "UI设计师", "上海黄浦区"));
            Toast.makeText(v.getContext(), "Person更新成功 -> 打印name -> " + name, Toast.LENGTH_SHORT).show();
        }

        public boolean onLongClick(View v) {
            Toast.makeText(v.getContext(), "长按点击事件", Toast.LENGTH_SHORT).show();
            return false;
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String id = s.toString();
            b.setUser(new User(id, id));
        }

        public void onLogin(View v, User user) {
            Log.e("Edwin", "isInflated() = " + b.viewStubMain.isInflated());
            if (!b.viewStubMain.isInflated()) {
                b.viewStubMain.getViewStub().inflate();
            }
            Toast.makeText(v.getContext(), "登录成功：" + user.getId(), Toast.LENGTH_SHORT).show();
        }

        public void startList(View v) {
            startActivity(new Intent(v.getContext(), ListActivity.class));
//            overridePendingTransition(R.anim.activity_enter_anim, R.anim.activity_exit_anim);
        }

        public void startRecyclerView(View v) {
            startActivity(new Intent(v.getContext(), RecyclerViewActivity.class));
//            overridePendingTransition(R.anim.activity_enter_anim, R.anim.activity_exit_anim);
        }
    }
}
