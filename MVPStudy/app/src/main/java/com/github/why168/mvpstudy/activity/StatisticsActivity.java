package com.github.why168.mvpstudy.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.github.why168.mvpstudy.Injection;
import com.github.why168.mvpstudy.R;
import com.github.why168.mvpstudy.base.BaseActivity;
import com.github.why168.mvpstudy.fragment.StatisticsFragment;
import com.github.why168.mvpstudy.statistics.StatisticsPresenter;
import com.github.why168.mvpstudy.utils.ActivityUtils;

public class StatisticsActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected int getLayoutResId() {
        return R.layout.statistics_act;
    }

    @Override
    protected void initializeView() {
        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle(R.string.statistics_title);
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        // Set up the navigation drawer.
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        StatisticsFragment statisticsFragment = (StatisticsFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);
        if (statisticsFragment == null) {
            statisticsFragment = StatisticsFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    statisticsFragment, R.id.contentFrame);
        }

        new StatisticsPresenter(Injection.provideTasksRepository(getApplicationContext()), statisticsFragment);

    }

    @Override
    protected void initializeData(Bundle savedInstanceState) {

    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.list_navigation_menu_item:
                                NavUtils.navigateUpFromSameTask(StatisticsActivity.this);
                                break;
                            case R.id.statistics_navigation_menu_item:
                                // Do nothing, we're already on that screen
                                break;
                            default:
                                break;
                        }
                        // Close the navigation drawer when an item is selected.
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }
}
