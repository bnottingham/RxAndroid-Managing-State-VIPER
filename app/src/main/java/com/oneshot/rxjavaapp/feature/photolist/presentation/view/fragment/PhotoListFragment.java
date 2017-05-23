package com.oneshot.rxjavaapp.feature.photolist.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.oneshot.rxjavaapp.R;
import com.oneshot.rxjavaapp.feature.photolist.presentation.events.SearchPhotosByTermUiEvent;
import com.oneshot.rxjavaapp.feature.photolist.presentation.model.PhotoModel;
import com.oneshot.rxjavaapp.feature.photolist.presentation.model.PhotoSearchUiModel;
import com.oneshot.rxjavaapp.feature.photolist.presentation.presenter.PhotoListPresenter;
import com.oneshot.rxjavaapp.feature.photolist.presentation.view.adapter.PhotoListAdapter;
import com.oneshot.rxjavaapp.presentation.view.fragment.BaseFragment;

import java.util.List;

/**
 * Created by brettn on 10/26/16.
 */
public class PhotoListFragment extends BaseFragment {
    private final static String KEY_CLEAR_CHECKBOX_STATE = "KEY_CLEAR_CHECKBOX_STATE";

    private PhotoListPresenter mPhotoListPresenter;

    private View mProgressBarContainer;
    private CheckedTextView mClearCheckBox;
    private Button mSubmitButton;
    private RecyclerView mRecyclerView;
    private PhotoListAdapter mPhotoListAdapter;

    public PhotoListFragment() {
        mPhotoListPresenter = new PhotoListPresenter(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_main;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPhotoListPresenter.onCreate();
    }

    @CallSuper
    @Override
    public void onViewReady(Bundle savedInstanceState) {
        mProgressBarContainer = findViewById(R.id.progress_bar_container);
        mClearCheckBox = (CheckedTextView) findViewById(R.id.submit_checkbox);
        mSubmitButton = (Button) findViewById(R.id.submit_button);
        mRecyclerView = (RecyclerView) findViewById(R.id.list);

        mPhotoListPresenter.handleSubmitClicked(RxView.clicks(mSubmitButton)
                .map(ignored -> new SearchPhotosByTermUiEvent("dog", mClearCheckBox.isChecked())));

        setupList();

        mPhotoListPresenter.onTakeView();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPhotoListPresenter.onDropView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPhotoListPresenter.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(KEY_CLEAR_CHECKBOX_STATE, mClearCheckBox.isChecked());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState == null) {
            return;
        }

        boolean refreshChecked = savedInstanceState.getBoolean(KEY_CLEAR_CHECKBOX_STATE);
        mClearCheckBox.setChecked(refreshChecked);
    }

    public void onPhotosReady(PhotoSearchUiModel refreshModel) {
        final List<PhotoModel> list = refreshModel.getPhotosAsList();
        if (refreshModel.shouldClearContent()) {
            mPhotoListAdapter.setData(list);
        } else {
            mPhotoListAdapter.appendData(list);
        }
    }

    public void setSubmitEnabled(boolean isEnabled) {
        mSubmitButton.setEnabled(isEnabled);
    }

    public void showProgressBar(boolean isVisible) {
        mProgressBarContainer.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    private void setupList() {
        mPhotoListAdapter = new PhotoListAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mPhotoListAdapter);
    }
}
