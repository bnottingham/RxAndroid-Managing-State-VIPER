package com.oneshot.rxjavaapp.feature.photolist.data.entity;

import com.oneshot.rxjavaapp.annotations.ApiSerializable;
import com.oneshot.rxjavaapp.state.data.Result;

/**
 * Created by brettn on 9/23/16.
 */
@ApiSerializable
public class PhotoEntity extends Result {

    String image_url;

    String url;

    public String getImageUrl() {
        return image_url;
    }
}
