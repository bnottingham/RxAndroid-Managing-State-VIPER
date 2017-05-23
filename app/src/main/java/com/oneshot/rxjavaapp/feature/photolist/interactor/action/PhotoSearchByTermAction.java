package com.oneshot.rxjavaapp.feature.photolist.interactor.action;

import com.oneshot.rxjavaapp.feature.photolist.presentation.events.SearchPhotosByTermUiEvent;
import com.oneshot.rxjavaapp.state.service.Action;

/**
 * Created by brettn on 5/9/17.
 */
public class PhotoSearchByTermAction extends Action {
    private final String mSearchTerm;
    private final boolean mClearContent;

    public PhotoSearchByTermAction(SearchPhotosByTermUiEvent event) {
        super(event);

        mSearchTerm = event.getSearchTerm();
        mClearContent = event.getClearContentOnSuccess();
    }

    public String getTerm() {
        return mSearchTerm;
    }

    public boolean shouldClearContent() {
        return mClearContent;
    }
}
