package com.oneshot.rxjavaapp.feature.photolist.interactor;

import android.text.TextUtils;

import com.oneshot.rxjavaapp.constants.PhotoApiConstants;
import com.oneshot.rxjavaapp.feature.photolist.data.entity.PhotosPageEntity;
import com.oneshot.rxjavaapp.feature.photolist.data.service.PhotosApi;
import com.oneshot.rxjavaapp.feature.photolist.interactor.action.PhotoSearchByTermAction;
import com.oneshot.rxjavaapp.interactor.BaseInteractor;
import com.oneshot.rxjavaapp.service.PhotoRetrofit;
import com.oneshot.rxjavaapp.state.service.Action;

import java.security.InvalidParameterException;

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
        return zipRequest(actionObservable.flatMap(action -> {
            if (TextUtils.isEmpty(action.getTerm())) {
                throw new InvalidParameterException("Empty search strings are not allowed");
            }
            return mPhotosApi.getPhotosByTerm(action.getTerm(), PhotoApiConstants.IMAGE_SIZE_CROPPED_440);
        }), actionObservable);
    }
}
