package com.github.why168.mvpstudy.fragment;

import android.view.View;
import android.widget.TextView;

import com.github.why168.mvpstudy.R;
import com.github.why168.mvpstudy.base.BaseFragment;
import com.github.why168.mvpstudy.statistics.StatisticsContract;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Edwin.Wu
 * @version 2017/5/30 23:02
 * @since JDK1.8
 */
public class StatisticsFragment extends BaseFragment implements StatisticsContract.View {

    private TextView mStatisticsTV;
    private StatisticsContract.Presenter mPresenter;

    public static StatisticsFragment newInstance() {
        return new StatisticsFragment();
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.statistics_frag;
    }

    @Override
    protected void initializeView(View root) {
        mStatisticsTV = (TextView) root.findViewById(R.id.statistics);
    }

    @Override
    protected void initializeData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }


    @Override
    public void setPresenter(StatisticsContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setProgressIndicator(boolean active) {
        if (active) {
            mStatisticsTV.setText(getString(R.string.loading));
        } else {
            mStatisticsTV.setText("");
        }
    }

    @Override
    public void showStatistics(int numberOfIncompleteTasks, int numberOfCompletedTasks) {
        if (numberOfCompletedTasks == 0 && numberOfIncompleteTasks == 0) {
            mStatisticsTV.setText(getResources().getString(R.string.statistics_no_tasks));
        } else {
            String displayString = getResources().getString(R.string.statistics_active_tasks) + " "
                    + numberOfIncompleteTasks + "\n" + getResources().getString(
                    R.string.statistics_completed_tasks) + " " + numberOfCompletedTasks;
            mStatisticsTV.setText(displayString);
        }
    }

    @Override
    public void showLoadingStatisticsError() {
        mStatisticsTV.setText(getResources().getString(R.string.statistics_error));
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
