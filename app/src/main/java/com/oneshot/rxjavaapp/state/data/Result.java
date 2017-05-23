package com.oneshot.rxjavaapp.state.data;

import com.oneshot.rxjavaapp.annotations.ApiSerializable;
import com.oneshot.rxjavaapp.state.service.Action;

/**
 * Created by brettn on 5/10/17.
 */
@ApiSerializable
public class Result {

    private Action mAction;

    public void setAction(Action action) {
        mAction = action;
    }

    public Action getAction() {
        return mAction;
    }
}
