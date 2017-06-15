package com.github.why168.mvpstudy.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.github.why168.mvpstudy.Injection;
import com.github.why168.mvpstudy.R;
import com.github.why168.mvpstudy.base.BaseActivity;
import com.github.why168.mvpstudy.fragment.TaskDetailFragment;
import com.github.why168.mvpstudy.taskdetail.TaskDetailPresenter;
import com.github.why168.mvpstudy.utils.ActivityUtils;

public class TaskDetailActivity extends BaseActivity {

    public static final String EXTRA_TASK_ID = "TASK_ID";

    @Override
    protected int getLayoutResId() {
        return R.layout.taskdetail_act;
    }

    @Override
    protected void initializeView() {
        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        // Get the requested task id
        String taskId = getIntent().getStringExtra(EXTRA_TASK_ID);

        TaskDetailFragment taskDetailFragment = (TaskDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (taskDetailFragment == null) {
            taskDetailFragment = TaskDetailFragment.newInstance(taskId);

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    taskDetailFragment, R.id.contentFrame);
        }

        // Create the presenter
        new TaskDetailPresenter(
                taskId,
                Injection.provideTasksRepository(getApplicationContext()),
                taskDetailFragment);
    }

    @Override
    protected void initializeData(Bundle savedInstanceState) {

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
