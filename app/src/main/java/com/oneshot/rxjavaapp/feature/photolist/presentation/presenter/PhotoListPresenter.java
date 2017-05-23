package com.oneshot.rxjavaapp.feature.photolist.presentation.presenter;


import com.oneshot.rxjavaapp.feature.photolist.interactor.PhotoListInteractor;
import com.oneshot.rxjavaapp.feature.photolist.interactor.action.PhotoSearchByTermAction;
import com.oneshot.rxjavaapp.feature.photolist.presentation.events.SearchPhotosByTermUiEvent;
import com.oneshot.rxjavaapp.feature.photolist.presentation.model.PhotoSearchUiModel;
import com.oneshot.rxjavaapp.feature.photolist.presentation.view.fragment.PhotoListFragment;
import com.oneshot.rxjavaapp.presentation.presenter.BasePresenter;
import com.oneshot.rxjavaapp.rx.ObservableTransformers;
import com.oneshot.rxjavaapp.state.model.UiModelState;

import io.reactivex.Observable;
import io.reactivex.exceptions.OnErrorNotImplementedException;

/**
 * Created by brettn on 4/25/17.
 */
public class PhotoListPresenter extends BasePresenter {
    private PhotoListFragment mView;
    private PhotoListInteractor mInteractor;

    public PhotoListPresenter(PhotoListFragment fragment) {
        mInteractor = new PhotoListInteractor();
        mView = fragment;
    }

    public void handleSubmitClicked(final Observable<SearchPhotosByTermUiEvent> events) {

        add(events.flatMap(event -> mInteractor.getPhotosByTerm(Observable.just(new PhotoSearchByTermAction(event)))
                .map(entity -> UiModelState.success(PhotoSearchUiModel.class, entity))
                .compose(ObservableTransformers.networkTransformer(PhotoSearchUiModel.class)))
                .subscribe(model -> {
                    mView.setSubmitEnabled(!model.isInProgress());
                    mView.showProgressBar(model.isInProgress());

                    if (!model.isInProgress() && model.isSuccess()) {
                        mView.onPhotosReady(model);
                    }
                }, throwable -> {
                    throw new OnErrorNotImplementedException(throwable);
                }));
    }
}
