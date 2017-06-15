package cool.oneshot.rxjavaapp.feature.photolist.data.entity;

import cool.oneshot.rxjavaapp.annotations.ApiSerializable;
import cool.oneshot.rxjavaapp.state.data.Result;

/**
 * Created by brettn on 9/21/16.
 */
@ApiSerializable
public class PhotosPageEntity extends Result {

    int current_page;


    int total_pages;


    int total_items;


    PhotoEntity[] photos;

    public int getCurrentPage() {
        return current_page;
    }

    public int getTotalPages() {
        return total_pages;
    }

    public int getTotalItems() {
        return total_items;
    }

    public PhotoEntity[] getPhotos() {
        return photos;
    }


}
