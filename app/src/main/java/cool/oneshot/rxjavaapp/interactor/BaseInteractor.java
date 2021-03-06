package cool.oneshot.rxjavaapp.interactor;

import cool.oneshot.rxjavaapp.state.data.Result;
import cool.oneshot.rxjavaapp.state.service.Action;

import io.reactivex.Observable;

/**
 * Created by brettn on 5/22/17.
 */
public class BaseInteractor {

    public <T> Observable<T> zipRequest(Observable<T> apiObservable, Observable<? extends Action> actionObservable) {
        return Observable.zip(
                apiObservable,
                actionObservable,
                (result, action) -> {
                    if (result instanceof Result) {
                        ((Result) result).setAction(action);
                    }
                    return result;
                });
    }
}
