package me.ritesh.wallpapers.view;

import android.os.Bundle;

/**
 * @author Ritesh Shakya
 */

public interface IView<M> {
    void onLoading();

    void onLoadingMore();

    void onComplete(M model);

    void onStartActivity(Class<?> cls, Bundle bundle);

    void onError(String message);
}
