package me.ritesh.wallpapers.view.presenter;

import me.ritesh.wallpapers.data.analytics.IAnalytics;

public interface Presenter<T, M> {
    void resume();

    void pause();

    void destroy();

    void stop();

    void loadData(Object... params);

    T getView();

    void setView(T view);

    M getModel();

    void setModel(M model);

    IAnalytics getAnalytics();
}


