package cool.oneshot.rxjavaapp.feature.photolist.interactor.action;

import cool.oneshot.rxjavaapp.feature.photolist.presentation.events.SearchPhotosByTermUiEvent;
import cool.oneshot.rxjavaapp.state.service.Action;

/**
 * Created by brettn on 5/9/17.
 */
public class PhotoSearchByTermAction extends Action {
    private final String mSearchTerm;
    private final boolean mClearContentOnSuccess;

    public PhotoSearchByTermAction(SearchPhotosByTermUiEvent event) {
        super(event);

        mSearchTerm = event.getSearchTerm();
        mClearContentOnSuccess = event.getClearContentOnSuccess();
    }

    public String getTerm() {
        return mSearchTerm;
    }

    public boolean shouldClearContentOnSuccess() {
        return mClearContentOnSuccess;
    }
}
