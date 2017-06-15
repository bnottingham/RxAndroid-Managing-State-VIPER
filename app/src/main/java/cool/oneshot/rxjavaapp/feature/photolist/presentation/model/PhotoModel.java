package cool.oneshot.rxjavaapp.feature.photolist.presentation.model;

import cool.oneshot.rxjavaapp.feature.photolist.data.entity.PhotoEntity;

/**
 * Created by brettn on 5/23/17.
 */
public class PhotoModel {
    private final String mImageUrl;


    public PhotoModel(PhotoEntity entity) {
        mImageUrl = entity.getImageUrl();
    }

    public String getImageUrl() {
        return mImageUrl;
    }
}
