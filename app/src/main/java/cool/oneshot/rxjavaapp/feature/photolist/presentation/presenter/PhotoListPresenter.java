package cool.oneshot.rxjavaapp.feature.photolist.presentation.presenter;


import cool.oneshot.rxjavaapp.feature.photolist.interactor.PhotoListInteractor;
import cool.oneshot.rxjavaapp.feature.photolist.interactor.action.PhotoSearchByTermAction;
import cool.oneshot.rxjavaapp.feature.photolist.presentation.events.SearchPhotosByTermUiEvent;
import cool.oneshot.rxjavaapp.feature.photolist.presentation.model.PhotoSearchUiModel;
import cool.oneshot.rxjavaapp.feature.photolist.presentation.view.fragment.PhotoListFragment;
import cool.oneshot.rxjavaapp.presentation.presenter.BasePresenter;
import cool.oneshot.rxjavaapp.rx.ObservableTransformers;
import cool.oneshot.rxjavaapp.state.model.UiModelState;

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
                    } else if (model.isFailure()) {
                        mView.showError(model.getError());
                    }
                }, throwable -> {
                    throw new OnErrorNotImplementedException(throwable);
                }));
    }
}
