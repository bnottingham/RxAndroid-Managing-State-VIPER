package com.oneshot.rxjavaapp.presentation.view.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by brettn on 4/25/17.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @LayoutRes
    public abstract int getLayoutResId();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(getLayoutResId());
    }
}
