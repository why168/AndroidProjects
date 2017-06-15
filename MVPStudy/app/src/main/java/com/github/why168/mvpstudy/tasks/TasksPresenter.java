package com.github.why168.mvpstudy.tasks;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.github.why168.mvpstudy.activity.AddEditTaskActivity;
import com.github.why168.mvpstudy.data.Task;
import com.github.why168.mvpstudy.data.source.TasksDataSource;
import com.github.why168.mvpstudy.data.source.TasksRepository;
import com.github.why168.mvpstudy.utils.EspressoIdlingResource;

import java.util.ArrayList;
import java.util.List;

import static com.github.why168.mvpstudy.tasks.TasksFilterType.ALL_TASKS;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Edwin.Wu
 * @version 2017/5/30 22:29
 * @since JDK1.8
 */
public class TasksPresenter implements TasksContract.Presenter {
    private final TasksRepository mTasksRepository;
    private final TasksContract.View mTasksView;
    private TasksFilterType mCurrentFiltering = ALL_TASKS;
    private boolean mFirstLoad = true;

    public TasksPresenter(TasksRepository tasksRepository, TasksContract.View tasksView) {
        mTasksRepository = checkNotNull(tasksRepository, "tasksRepository cannot be null");
        mTasksView = checkNotNull(tasksView, "tasksView cannot be null!");

        mTasksView.setPresenter(this);
    }

    @Override
    public void start() {
        loadTasks(false);
    }

    @Override
    public void result(int requestCode, int resultCode) {
        // 如果一个任务被成功添加, show snackbar
        if (AddEditTaskActivity.REQUEST_ADD_TASK == requestCode && Activity.RESULT_OK == resultCode) {
            mTasksView.showSuccessfullySavedMessage();
        }
    }

    @Override
    public void loadTasks(boolean forceUpdate) {
        //TODO 简化示例:一个网络重新加载将被迫在第一次加载。
        loadTasks(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    private void loadTasks(boolean forceUpdate, final boolean showLoadingUI) {
        if (showLoadingUI) {
            mTasksView.setLoadingIndicator(true);
        }
        if (forceUpdate) {
            mTasksRepository.refreshTasks();
        }

        // 网络请求可能是在一个不同的线程中处理所以确保Espresso知道
        // 之前,应用程序正忙着处理的响应。
        EspressoIdlingResource.increment(); // 应用程序正忙着,直到另行通知


        mTasksRepository.getTasks(new TasksDataSource.LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                List<Task> tasksToShow = new ArrayList<Task>();

                // 这个回调可称为两次,一次加载缓存和一次
                // 来自服务器的数据API,所以我们之前检查递减,否则
                // 抛出“计数器已损坏!“例外。
                if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                    EspressoIdlingResource.decrement(); // 应用程序设置为闲置。
                }

                // 我们基于requestType过滤任务
                for (Task task : tasks) {
                    switch (mCurrentFiltering) {
                        case ALL_TASKS:
                            tasksToShow.add(task);
                            break;
                        case ACTIVE_TASKS:
                            if (task.isActive()) {
                                tasksToShow.add(task);
                            }
                            break;
                        case COMPLETED_TASKS:
                            if (task.isCompleted()) {
                                tasksToShow.add(task);
                            }
                            break;
                        default:
                            tasksToShow.add(task);
                            break;
                    }
                }

                // 视图可能无法处理UI更新了
                if (!mTasksView.isActive()) {
                    return;
                }
                if (showLoadingUI) {
                    mTasksView.setLoadingIndicator(false);
                }

                processTasks(tasksToShow);

            }

            @Override
            public void onDataNotAvailable() {
                // 视图可能无法处理UI更新了
                if (!mTasksView.isActive()) {
                    return;
                }
                mTasksView.showLoadingTasksError();
            }
        });


    }

    private void processTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            // 显示一条消息说明没有任务,滤波器类型.
            processEmptyTasks();
        } else {
            // 显示任务的列表
            mTasksView.showTasks(tasks);
            // 设置过滤标签的文本
            showFilterLabel();
        }
    }

    private void showFilterLabel() {
        switch (mCurrentFiltering) {
            case ACTIVE_TASKS:
                mTasksView.showActiveFilterLabel();
                break;
            case COMPLETED_TASKS:
                mTasksView.showCompletedFilterLabel();
                break;
            default:
                mTasksView.showAllFilterLabel();
                break;
        }
    }

    private void processEmptyTasks() {
        switch (mCurrentFiltering) {
            case ACTIVE_TASKS:
                mTasksView.showNoActiveTasks();
                break;
            case COMPLETED_TASKS:
                mTasksView.showNoCompletedTasks();
                break;
            default:
                mTasksView.showNoTasks();
                break;
        }
    }

    @Override
    public void addNewTask() {
        mTasksView.showAddTask();
    }

    @Override
    public void openTaskDetails(@NonNull Task requestedTask) {
        checkNotNull(requestedTask, "requestedTask cannot be null!");
        mTasksView.showTaskDetailsUi(requestedTask.getId());
    }

    @Override
    public void completeTask(@NonNull Task completedTask) {
        checkNotNull(completedTask, "completedTask cannot be null!");
        mTasksRepository.completeTask(completedTask);
        mTasksView.showTaskMarkedComplete();
        loadTasks(false, false);
    }

    @Override
    public void activateTask(@NonNull Task activeTask) {
        checkNotNull(activeTask, "activeTask cannot be null!");
        mTasksRepository.activateTask(activeTask);
        mTasksView.showTaskMarkedActive();
        loadTasks(false, false);
    }

    @Override
    public void clearCompletedTasks() {
        mTasksRepository.clearCompletedTasks();
        mTasksView.showCompletedTasksCleared();
        loadTasks(false, false);
    }

    /**
     * 设置当前任务过滤类型。
     *
     * @param requestType Can be {@link TasksFilterType#ALL_TASKS},
     *                    {@link TasksFilterType#COMPLETED_TASKS}, or
     *                    {@link TasksFilterType#ACTIVE_TASKS}
     */
    @Override
    public void setFiltering(TasksFilterType requestType) {
        mCurrentFiltering = requestType;
    }

    @Override
    public TasksFilterType getFiltering() {
        return mCurrentFiltering;
    }
}
