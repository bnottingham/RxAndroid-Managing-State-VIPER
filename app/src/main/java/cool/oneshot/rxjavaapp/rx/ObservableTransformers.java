package cool.oneshot.rxjavaapp.rx;

import android.os.AsyncTask;

import cool.oneshot.rxjavaapp.state.model.UiModelState;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Custom {@link ObservableTransformer}s that can be shared for handling common transformations
 * of an input {@link Observable} to an output {@link Observable}.
 * <p>
 * For example, probably the most common transformation to apply to a given {@link Observable} is
 * to apply a specific threading model to it so that subscribing occurs on one thread and observing
 * occurs on another thread. The {@link ObservableTransformer}s provided in this utility class
 * can be used to share pre-defined threading models.
 * <p>
 * A primary purpose of this class is to allow these commonly-used policies to be applied to instances
 * of {@link Observable}s used throughout the SDK without having to duplicate the RxJava-specific
 * code to implement the policies for a given {@link Observable}.
 */
public final class ObservableTransformers {

    private static final ObservableTransformer<Observable, Observable> SCHEDULERS_TRANSFORMER_SUBSCRIBE_IO_OBSERVE_MAIN =
            applySchedulers(Schedulers.io(), AndroidSchedulers.mainThread());

    private static final ObservableTransformer<Observable, Observable> SCHEDULERS_TRANSFORMER_SUBSCRIBE_ASYNC_OBSERVE_MAIN =
            applySchedulers(Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR), AndroidSchedulers.mainThread());

    private static final ObservableTransformer<Observable, Observable> SCHEDULERS_TRANSFORMER_SUBSCRIBE_ASYNC_OBSERVE_ASYNC =
            applySchedulers(Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR), Schedulers.trampoline());

    private static final ObservableTransformer<Observable, Observable> SCHEDULERS_TRANSFORMER_SUBSCRIBE_IO_OBSERVE_IO =
            applySchedulers(Schedulers.io(), Schedulers.trampoline());

    private static final ObservableTransformer<Observable, Observable> SCHEDULERS_TRANSFORMER_SUBSCRIBE_MAIN_OBSERVE_MAIN =
            applySchedulers(AndroidSchedulers.mainThread(), AndroidSchedulers.mainThread());

    /**
     * Transformer for subscribing on a background I/O thread and observing on the main thread.
     * <p>
     * Note: The background thread that subscription actions are performed on is taken from the
     * {@link Schedulers#io()} provided by RxJava.
     *
     * @param <T> the type of {@link Observable} that will be transformed
     * @return a {@link ObservableTransformer} that applies the given threading policy
     */
    @SuppressWarnings("unchecked")
    public static <T> ObservableTransformer<T, T> applySchedulersSubscribeOnIoObserveOnMain() {
        return (ObservableTransformer<T, T>) SCHEDULERS_TRANSFORMER_SUBSCRIBE_IO_OBSERVE_MAIN;
    }

    /**
     * Transformer for subscribing on a background thread and observing on the main thread.
     * <p>
     * Note: The background thread that subscription actions are performed on is taken from the thread
     * pool provided by the Android framework's {@link AsyncTask#THREAD_POOL_EXECUTOR}.
     *
     * @param <T> the type of {@link Observable} that will be transformed
     * @return a {@link ObservableTransformer} that applies the given threading policy
     */
    @SuppressWarnings("unchecked")
    public static <T> ObservableTransformer<T, T> applySchedulersSubscribeOnAsyncObserveOnMain() {
        return (ObservableTransformer<T, T>) SCHEDULERS_TRANSFORMER_SUBSCRIBE_ASYNC_OBSERVE_MAIN;
    }

    /**
     * Transformer for both subscribing and observing on the same background thread.
     * <p>
     * Note: The background thread that subscription actions are performed on is taken from the thread
     * pool provided by the Android framework's {@link AsyncTask#THREAD_POOL_EXECUTOR}.
     *
     * @param <T> the type of {@link Observable} that will be transformed
     * @return a {@link ObservableTransformer} that applies the given threading policy
     */
    @SuppressWarnings("unchecked")
    public static <T> ObservableTransformer<T, T> applySchedulersSubscribeOnAsyncObserveOnAsync() {
        return (ObservableTransformer<T, T>) SCHEDULERS_TRANSFORMER_SUBSCRIBE_ASYNC_OBSERVE_ASYNC;
    }

    /**
     * Transformer for both subscribing and observing on the same I/O thread.
     * <p>
     * Note: The background threads that subscription actions are performed on and notifications are
     * sent (observed) on is taken from the {@link Schedulers#io()} provided by RxJava.
     *
     * @param <T> the type of {@link Observable} that will be transformed
     * @return a {@link ObservableTransformer} that applies the given threading policy
     */
    @SuppressWarnings("unchecked")
    public static <T> ObservableTransformer<T, T> applySchedulersSubscribeOnIoObserveOnIo() {
        return (ObservableTransformer<T, T>) SCHEDULERS_TRANSFORMER_SUBSCRIBE_IO_OBSERVE_IO;
    }

    /**
     * Transformer for subscribing on the main thread and observing on the main thread.
     *
     * @param <T> the type of {@link Observable} that will be transformed
     * @return a {@link ObservableTransformer} that applies the given threading policy
     */
    @SuppressWarnings("unchecked")
    public static <T> ObservableTransformer<T, T> applySchedulersSubscribeOnMainObserveOnMain() {
        return (ObservableTransformer<T, T>) SCHEDULERS_TRANSFORMER_SUBSCRIBE_MAIN_OBSERVE_MAIN;
    }

    /**
     * Transformer for applying a threading policy to an {@link Observable} in which it subscribes on the
     * given subscribe {@link Scheduler} thread and observes on the given observe {@link Scheduler}
     *
     * @param subscribeScheduler the {@link Scheduler} to perform subscription actions on
     * @param observeScheduler   the {@link Scheduler} to observe on
     * @param <T>                the type of {@link Observable} that will be transformed
     * @return a {@link ObservableTransformer} that applies the given threading policy
     */
    public static <T> ObservableTransformer<T, T> applySchedulers(final Scheduler subscribeScheduler, final Scheduler observeScheduler) {
        return observable -> observable.subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler);
    }

    /**
     * Transformer for applying a complete UiModel lifecycle to the stream {@link Observable} for async requests
     * Will observe on Async and Subscribe on Main threads
     *
     * @param classType should extend UiModel, used for error handling and state management
     * @param <T>
     * @return stream observable that handles progress, error, and thread management
     */
    public static <T> ObservableTransformer<T, T> networkTransformer(Class<T> classType) {
        return events -> events
                .onErrorReturn(t -> UiModelState.failure(classType, t))
                .compose(ObservableTransformers.applySchedulersSubscribeOnAsyncObserveOnMain())
                .startWith(UiModelState.inProgress(classType));
    }

    private ObservableTransformers() {
    }
}
