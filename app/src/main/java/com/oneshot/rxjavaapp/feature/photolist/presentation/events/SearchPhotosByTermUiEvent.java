package com.oneshot.rxjavaapp.feature.photolist.presentation.events;

import com.oneshot.rxjavaapp.state.event.UiEvent;

/**
 * Created by brettn on 4/21/17.
 */
public class SearchPhotosByTermUiEvent extends UiEvent {

    private final String mSearchTerm;
    private final boolean mClearContentOnSuccess;


    public SearchPhotosByTermUiEvent(final String searchTerm,
                                     final boolean clearContentOnSuccess) {
        mSearchTerm = searchTerm;
        mClearContentOnSuccess = clearContentOnSuccess;
    }

    public String getSearchTerm() {
        return mSearchTerm;
    }

    public boolean getClearContentOnSuccess() {
        return mClearContentOnSuccess;
    }
}
