package me.ritesh.wallpapers.view.presenter;

import me.ritesh.wallpapers.domain.analytics.IAnalytics;

public interface Presenter<T, M> {

    void destroy();

    void stop();

    void loadData(Object... params);

    T getView();

    void setView(T view);

    M getModel();

    void setModel(M model);

    IAnalytics getAnalytics();
}


