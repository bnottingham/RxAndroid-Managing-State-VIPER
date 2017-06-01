package com.oneshot.rxjavaapp.feature.photolist.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Toast;

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
    private CheckedTextView mClearResultsOnSuccessCheckBox;
    private EditText mSearchQueryEditText;
    private Button mSearchPhotosButton;
    private RecyclerView mPhotoListRecyclerView;
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
        mProgressBarContainer = findViewById(R.id.search_progress_bar_container);
        mClearResultsOnSuccessCheckBox = (CheckedTextView) findViewById(R.id.search_checkbox_clear_results_on_success);
        mSearchPhotosButton = (Button) findViewById(R.id.search_button_submit);
        mPhotoListRecyclerView = (RecyclerView) findViewById(R.id.search_photo_list_results);
        mSearchQueryEditText = (EditText) findViewById(R.id.search_edittext_query);

        mPhotoListPresenter.handleSubmitClicked(RxView.clicks(mSearchPhotosButton)
                .map(ignored -> new SearchPhotosByTermUiEvent(mSearchQueryEditText.getText().toString(), mClearResultsOnSuccessCheckBox.isChecked())));

        setupList();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPhotoListPresenter.onTakeView();
    }

    @Override
    public void onPause() {
        super.onPause();
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

        outState.putBoolean(KEY_CLEAR_CHECKBOX_STATE, mClearResultsOnSuccessCheckBox.isChecked());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState == null) {
            return;
        }

        boolean refreshChecked = savedInstanceState.getBoolean(KEY_CLEAR_CHECKBOX_STATE);
        mClearResultsOnSuccessCheckBox.setChecked(refreshChecked);
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
        mSearchPhotosButton.setEnabled(isEnabled);
    }

    public void showProgressBar(boolean isVisible) {
        mProgressBarContainer.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    private void setupList() {
        mPhotoListAdapter = new PhotoListAdapter();
        mPhotoListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mPhotoListRecyclerView.setAdapter(mPhotoListAdapter);
    }

    public void showError(Throwable error) {
        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
    }
}
