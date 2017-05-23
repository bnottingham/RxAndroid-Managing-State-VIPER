package com.oneshot.rxjavaapp.state.service;

import com.oneshot.rxjavaapp.state.event.UiEvent;

/**
 * Created by brettn on 5/10/17.
 */
public class Action {
    private final UiEvent mUiEvent;

    public Action(final UiEvent uiEvent) {
        mUiEvent = uiEvent;
    }

    public UiEvent getUiEvent() {
        return mUiEvent;
    }
}
