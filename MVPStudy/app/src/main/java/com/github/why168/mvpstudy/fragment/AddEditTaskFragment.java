package com.github.why168.mvpstudy.fragment;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.github.why168.mvpstudy.R;
import com.github.why168.mvpstudy.addedittask.AddEditTaskContract;
import com.github.why168.mvpstudy.base.BaseFragment;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * AddEditTaskFragment
 *
 * @author Edwin.Wu
 * @version 2017/6/2 10:48
 * @since JDK1.8
 */
public class AddEditTaskFragment extends BaseFragment implements AddEditTaskContract.View {

    public static final String ARGUMENT_EDIT_TASK_ID = "EDIT_TASK_ID";
    private AddEditTaskContract.Presenter mPresenter;
    private TextView mTitle;
    private TextView mDescription;

    public AddEditTaskFragment() {
        // Required empty public constructor
    }

    public static AddEditTaskFragment newInstance() {
        return new AddEditTaskFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.addtask_frag;
    }

    @Override
    protected void initializeView(View root) {
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab_edit_task_done);
        fab.setImageResource(R.drawable.ic_done);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.saveTask(mTitle.getText().toString(), mDescription.getText().toString());
            }
        });

        mTitle = (TextView) root.findViewById(R.id.add_task_title);
        mDescription = (TextView) root.findViewById(R.id.add_task_description);
        setHasOptionsMenu(true);
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
    public void setPresenter(@NonNull AddEditTaskContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showEmptyTaskError() {
        Snackbar.make(mTitle, getString(R.string.empty_task_message), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showTasksList() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void setTitle(String title) {
        mTitle.setText(title);
    }

    @Override
    public void setDescription(String description) {
        mDescription.setText(description);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
