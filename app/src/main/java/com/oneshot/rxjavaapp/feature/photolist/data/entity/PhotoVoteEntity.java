package com.oneshot.rxjavaapp.feature.photolist.data.entity;

import com.oneshot.rxjavaapp.annotations.ApiSerializable;
import com.oneshot.rxjavaapp.state.data.Result;

/**
 * Created by brettn on 10/17/16.
 */
@ApiSerializable
public class PhotoVoteEntity extends Result {

    int current_page;


    int total_pages;


    int total_items;


    UserEntity[] users;
}
