package com.oneshot.rxjavaapp.feature.photolist.data.service;

import com.oneshot.rxjavaapp.feature.photolist.data.entity.PhotoVoteEntity;
import com.oneshot.rxjavaapp.feature.photolist.data.entity.PhotosPageEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by brettn on 9/21/16.
 */
public interface PhotosApi {
    @GET("photos?feature=fresh_today&sort=created_at&page=1&limit=10&image_size=4&include_store=store_download&include_states=voted")
    Observable<PhotosPageEntity> getFreshTodayPhotos();

    @GET("photos/search")
    Observable<PhotosPageEntity> getPhotosByTerm(@Query("term") String queryTerm);

    @GET("photos/{id}/votes?")
    Observable<PhotoVoteEntity> getPhotoLikes(@Path("id") int id);
}
