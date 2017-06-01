package com.oneshot.rxjavaapp.state.model;

import com.oneshot.rxjavaapp.state.data.Result;

import java.lang.reflect.Constructor;

/**
 * Created by brettn on 5/23/17.
 */
public class UiModelState {

    public static <T> T success(Class<T> uiClass, Result result) {
        try {
            Constructor constructor = uiClass.getConstructors()[0];
            Object object = constructor.newInstance(new Object[]{false, true, null, result});
            return (T) object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T failure(Class<T> uiClass, Throwable throwable) {
        try {
            Constructor constructor = uiClass.getConstructors()[0];
            Object object = constructor.newInstance(new Object[]{false, false, throwable, null});
            return (T) object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T inProgress(Class<T> uiClass) {
        try {
            Constructor constructor = uiClass.getConstructors()[0];
            Object object = constructor.newInstance(new Object[]{true, false, null, null});
            return (T) object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
