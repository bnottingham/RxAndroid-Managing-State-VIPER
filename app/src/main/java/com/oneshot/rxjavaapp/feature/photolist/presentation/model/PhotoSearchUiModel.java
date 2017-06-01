package com.oneshot.rxjavaapp.feature.photolist.presentation.model;

import com.oneshot.rxjavaapp.feature.photolist.data.entity.PhotosPageEntity;
import com.oneshot.rxjavaapp.feature.photolist.data.mapper.PhotoModelMapper;
import com.oneshot.rxjavaapp.feature.photolist.interactor.action.PhotoSearchByTermAction;
import com.oneshot.rxjavaapp.state.model.UiModel;
import com.oneshot.rxjavaapp.state.service.Action;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by brettn on 4/24/17.
 */
public class PhotoSearchUiModel extends UiModel<PhotosPageEntity> {
    private List<PhotoModel> mPhotoList;
    private boolean mShouldClearContentOnSuccess;

    public PhotoSearchUiModel(boolean inProgress, boolean success, Throwable error, PhotosPageEntity result) {
        super(inProgress, success, error, result);
    }

    public List<PhotoModel> getPhotosAsList() {
        return mPhotoList == null ? Collections.emptyList() : mPhotoList;
    }

    public boolean shouldClearContentOnSuccess() {
        return mShouldClearContentOnSuccess;
    }

    @Override
    protected void onResultReady(PhotosPageEntity result) {
        mPhotoList = PhotoModelMapper.transform(Arrays.asList(result.getPhotos()));

        Action action = result.getAction();
        if (action instanceof PhotoSearchByTermAction) {
            mShouldClearContentOnSuccess = ((PhotoSearchByTermAction) action).shouldClearContentOnSuccess();
        }
    }
}
