package com.oneshot.rxjavaapp.feature.photolist.interactor;

import com.oneshot.rxjavaapp.feature.photolist.data.entity.PhotosPageEntity;
import com.oneshot.rxjavaapp.feature.photolist.data.service.PhotosApi;
import com.oneshot.rxjavaapp.feature.photolist.interactor.action.PhotoSearchByTermAction;
import com.oneshot.rxjavaapp.interactor.BaseInteractor;
import com.oneshot.rxjavaapp.service.PhotoRetrofit;
import com.oneshot.rxjavaapp.state.service.Action;

import io.reactivex.Observable;

/**
 * Created by brettn on 4/25/17.
 */
public class PhotoListInteractor extends BaseInteractor {
    private PhotosApi mPhotosApi;

    public PhotoListInteractor() {
        mPhotosApi = PhotoRetrofit.getRetrofit().create(PhotosApi.class);
    }

    public Observable<PhotosPageEntity> getFreshTodayPhotos(final Observable<Action> actionObservable) {
        return zipRequest(actionObservable.flatMap(action -> mPhotosApi.getFreshTodayPhotos()), actionObservable);
    }

    public Observable<PhotosPageEntity> getPhotosByTerm(final Observable<PhotoSearchByTermAction> actionObservable) {
        return zipRequest(actionObservable.flatMap(action -> mPhotosApi.getPhotosByTerm(action.getTerm())), actionObservable);
    }
}
