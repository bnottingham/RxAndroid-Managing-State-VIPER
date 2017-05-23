package com.oneshot.rxjavaapp.feature.photolist.presentation.model;

import com.oneshot.rxjavaapp.feature.photolist.data.entity.PhotoEntity;

/**
 * Created by brettn on 5/23/17.
 */
public class PhotoModel {
    private PhotoEntity mPhotoEntity;

    private final String mImageUrl;


    public PhotoModel(PhotoEntity entity) {
        mPhotoEntity = entity;
        mImageUrl = mPhotoEntity.getImageUrl();
    }

    public String getImageUrl() {
        return mImageUrl;
    }
}
