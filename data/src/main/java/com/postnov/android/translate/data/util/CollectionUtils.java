package com.postnov.android.translate.data.util;

import java.util.Collection;

/**
 * @author Valentin Postnov
 */
public class CollectionUtils {

    public static <T> Collection<T> doOnEach(Collection<T> collection, Action<T> action) {
        for (T value : collection) {
            action.call(value);
        }

        return collection;
    }

    public interface Action<T> {
        void call(T value);
    }
}
