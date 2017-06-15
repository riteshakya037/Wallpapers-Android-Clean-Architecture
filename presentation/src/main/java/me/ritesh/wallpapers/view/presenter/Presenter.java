package me.ritesh.wallpapers.view.presenter;

public interface Presenter<T, M> {
    void resume();

    void pause();

    void destroy();

    void stop();

    void loadData(Object... params);

    void setView(T view);

    M getModel();

    void setModel(M model);
}


