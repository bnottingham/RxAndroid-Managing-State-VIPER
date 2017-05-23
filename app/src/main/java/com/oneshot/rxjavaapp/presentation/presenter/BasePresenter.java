package com.oneshot.rxjavaapp.presentation.presenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by brettn on 4/25/17.
 */

public class BasePresenter implements PresenterInterface {
    private CompositeDisposable mDisposables = new CompositeDisposable();

    public BasePresenter() {
    }

    protected void add(Disposable disposable) {
        mDisposables.add(disposable);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onTakeView() {

    }

    @Override
    public void onDropView() {

    }

    @Override
    public void onDestroy() {
        mDisposables.dispose();
    }
}
