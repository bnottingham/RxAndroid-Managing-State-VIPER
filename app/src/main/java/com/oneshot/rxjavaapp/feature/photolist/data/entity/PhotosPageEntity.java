package com.oneshot.rxjavaapp.feature.photolist.data.entity;

import com.oneshot.rxjavaapp.annotations.ApiSerializable;
import com.oneshot.rxjavaapp.state.data.Result;

/**
 * Created by brettn on 9/21/16.
 */
@ApiSerializable
public class PhotosPageEntity extends Result {

    int current_page;


    int total_pages;


    int total_items;


    PhotoEntity[] photos;

    public PhotoEntity[] getPhotos() {
        return photos;
    }
}
