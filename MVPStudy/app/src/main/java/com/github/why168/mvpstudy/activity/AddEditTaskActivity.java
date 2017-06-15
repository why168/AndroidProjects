package com.github.why168.mvpstudy.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.github.why168.mvpstudy.Injection;
import com.github.why168.mvpstudy.R;
import com.github.why168.mvpstudy.addedittask.AddEditTaskPresenter;
import com.github.why168.mvpstudy.base.BaseActivity;
import com.github.why168.mvpstudy.fragment.AddEditTaskFragment;
import com.github.why168.mvpstudy.utils.ActivityUtils;
import com.github.why168.mvpstudy.utils.EspressoIdlingResource;

/**
 * AddEditTaskActivity
 *
 * @author Edwin.Wu
 * @version 2017/6/1 07:49
 * @since JDK1.8
 */
public class AddEditTaskActivity extends BaseActivity {

    public static int REQUEST_ADD_TASK = 1;
    public static final String SHOULD_LOAD_DATA_FROM_REPO_KEY = "SHOULD_LOAD_DATA_FROM_REPO_KEY";
    private AddEditTaskPresenter mAddEditTaskPresenter;
    private ActionBar mActionBar;
    private AddEditTaskFragment addEditTaskFragment;

    @Override
    protected int getLayoutResId() {
        return R.layout.addtask_act;
    }

    @Override
    protected void initializeView() {

        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(true);
        addEditTaskFragment = (AddEditTaskFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
    }

    @Override
    protected void initializeData(Bundle savedInstanceState) {

        String taskId = getIntent().getStringExtra(AddEditTaskFragment.ARGUMENT_EDIT_TASK_ID);

        setToolbarTitle(taskId);

        if (addEditTaskFragment == null) {
            addEditTaskFragment = AddEditTaskFragment.newInstance();

            if (getIntent().hasExtra(AddEditTaskFragment.ARGUMENT_EDIT_TASK_ID)) {
                Bundle bundle = new Bundle();
                bundle.putString(AddEditTaskFragment.ARGUMENT_EDIT_TASK_ID, taskId);
                addEditTaskFragment.setArguments(bundle);
            }

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), addEditTaskFragment, R.id.contentFrame);
        }

        boolean shouldLoadDataFromRepo = true;

        // Prevent the presenter from loading data from the repository if this is a config change.
        if (savedInstanceState != null) {
            // Data might not have loaded when the config change happen, so we saved the state.
            shouldLoadDataFromRepo = savedInstanceState.getBoolean(SHOULD_LOAD_DATA_FROM_REPO_KEY);
        }

        // Create the presenter
        mAddEditTaskPresenter = new AddEditTaskPresenter(
                taskId,
                Injection.provideTasksRepository(getApplicationContext()),
                addEditTaskFragment,
                shouldLoadDataFromRepo);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Save the state so that next time we know if we need to refresh data.
        outState.putBoolean(SHOULD_LOAD_DATA_FROM_REPO_KEY, mAddEditTaskPresenter.isDataMissing());
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }

    private void setToolbarTitle(@Nullable String taskId) {
        if (taskId == null) {
            mActionBar.setTitle(R.string.add_task);
        } else {
            mActionBar.setTitle(R.string.edit_task);
        }
    }
}
