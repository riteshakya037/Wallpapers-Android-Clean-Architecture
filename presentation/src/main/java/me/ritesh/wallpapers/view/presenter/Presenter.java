package me.ritesh.wallpapers.view.presenter;

import me.ritesh.wallpapers.data.analytics.IAnalytics;
import me.ritesh.wallpapers.view.IView;

public interface Presenter<T extends IView<M>, M> {
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


