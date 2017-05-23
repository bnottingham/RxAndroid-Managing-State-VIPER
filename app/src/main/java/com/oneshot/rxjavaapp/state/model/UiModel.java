package com.oneshot.rxjavaapp.state.model;

/**
 * Created by brettn on 5/10/17.
 */
public abstract class UiModel<T> {

    final boolean mInProgress;
    final boolean mSuccess;
    final String mErrorMessage;
    final T mResult;

    public UiModel(boolean inProgress, boolean success, String errorMessage, T result) {
        mInProgress = inProgress;
        mSuccess = success;
        mErrorMessage = errorMessage;
        mResult = result;

        if (mResult != null) {
            onResultReady(mResult);
        }
    }

    public boolean isInProgress() {
        return mInProgress;
    }

    public boolean isSuccess() {
        return mSuccess;
    }

    protected abstract void onResultReady(T result);
}
