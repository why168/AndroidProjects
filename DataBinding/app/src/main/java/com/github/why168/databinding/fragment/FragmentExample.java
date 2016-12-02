package com.github.why168.databinding.fragment;

import com.github.why168.databinding.R;
import com.github.why168.databinding.base.BaseFragment;
import com.github.why168.databinding.databinding.FragmentExampleBinding;

/**
 * FragmentExample
 *
 * @author Edwin.Wu
 * @version 2016/11/25 16:25
 * @since JDK1.8
 */
public class FragmentExample extends BaseFragment<FragmentExampleBinding> {
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_example;
    }

    @Override
    protected void setUpData() {
        b.textFragmentInfo.setText("fragment");
    }
}
