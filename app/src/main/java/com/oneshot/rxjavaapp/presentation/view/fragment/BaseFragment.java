package com.oneshot.rxjavaapp.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by brettn on 4/21/17.
 */
public abstract class BaseFragment extends Fragment {

    @LayoutRes
    public abstract int getLayoutResId();

    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(getLayoutResId(), container, false);

        onViewReady(savedInstanceState);
        return mRootView;
    }

    public abstract void onViewReady(final Bundle savedInstanceState);

    @Nullable
    @Override
    public View getView() {
        View view = super.getView();
        if (view == null) {
            view = mRootView;
        }
        return view;
    }

    @Nullable
    public final View findViewById(@IdRes int id) {
        return getView().findViewById(id);
    }
}
