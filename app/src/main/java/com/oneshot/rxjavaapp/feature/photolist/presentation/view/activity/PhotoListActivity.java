package com.oneshot.rxjavaapp.feature.photolist.presentation.view.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.oneshot.rxjavaapp.R;
import com.oneshot.rxjavaapp.feature.photolist.presentation.view.fragment.PhotoListFragment;
import com.oneshot.rxjavaapp.presentation.view.activity.BaseActivity;

/**
 * Created by brettn on 4/27/17.
 */
public class PhotoListActivity extends BaseActivity {
    private static final String TAG = PhotoListActivity.class.getSimpleName();

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_fragment_container);
        if (fragment == null) {
            fragment = new PhotoListFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_fragment_container, fragment)
                    .commit();
        }
    }
}
