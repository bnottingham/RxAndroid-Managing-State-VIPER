package com.oneshot.rxjavaapp.state.model;

/**
 * Created by brettn on 5/10/17.
 */
public abstract class UiModel<T> {

    final boolean mInProgress;
    final boolean mSuccess;
    final Throwable mThrowable;
    final T mResult;

    protected abstract void onResultReady(T result);

    public UiModel(boolean inProgress, boolean success, Throwable throwable, T result) {
        mInProgress = inProgress;
        mSuccess = success;
        mThrowable = throwable;
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

    public boolean isFailure() {
        return mThrowable != null;
    }

    public Throwable getError() {
        return mThrowable;
    }
}
