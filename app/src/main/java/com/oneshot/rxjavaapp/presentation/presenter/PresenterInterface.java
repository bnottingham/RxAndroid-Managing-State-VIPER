package com.oneshot.rxjavaapp.presentation.presenter;


/**
 * Base interface representing a Presenter in a Model-View-Presenter (MVP) UI module.
 * <p>
 * Defines contract for handling Android view components' lifecycle events - i.e. on create, resume, pause,
 * and destroy for {@link android.app.Fragment}s and/or {@link android.app.Activity}s and/or {@link android.view.View}
 */
public interface PresenterInterface {

    /**
     * Used to handle the creation of a newly-created view.
     * This should be called during Activity's or Fragment's onCreate(), or during android.view.View#onFinishInflate().
     */
    void onCreate();

    /**
     * Used to handle the resuming of a previously-paused or newly-created view.
     * This should be called during Activity's or Fragment's onResume(), or during android.view.View#onAttachedToWindow().
     */
    void onTakeView();


    /**
     * Used to handle the pausing of a previously-resumed and active (visible) view.
     * This should be called during Activity's or Fragment's onPause(), not applicable for a View.
     */
    void onDropView();

    /**
     * Used to handle the destruction of an existing view.
     * This should be called during Activity's or Fragment's onDestroy(), or during android.view.View#onDetachedFromWindow().
     */
    void onDestroy();
}