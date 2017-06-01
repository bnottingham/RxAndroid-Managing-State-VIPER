package cool.oneshot.rxjavaapp.state.data;

import cool.oneshot.rxjavaapp.annotations.ApiSerializable;
import cool.oneshot.rxjavaapp.state.service.Action;

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
