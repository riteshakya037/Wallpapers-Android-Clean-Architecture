package me.ritesh.wallpapers.view.presenter;

import android.support.annotation.NonNull;

import dagger.internal.Preconditions;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.ritesh.wallpapers.data.analytics.IAnalytics;
import me.ritesh.wallpapers.domain.interactor.BaseInteractor;
import me.ritesh.wallpapers.mapper.IModelDataMapper;
import me.ritesh.wallpapers.view.IView;

/**
 * @author Ritesh Shakya
 */

public abstract class BasePresenter<T extends IView, M, K> implements Presenter<T, M> {
    private final BaseInteractor interactor;
    private final IModelDataMapper<K, M> modelDataMapper;
    private final IAnalytics analytics;
    private T view;
    private M model;

    protected BasePresenter(@NonNull BaseInteractor interactor, @NonNull IModelDataMapper<K, M> modelDataMapper, IAnalytics analytics) {
        this.interactor = Preconditions.checkNotNull(interactor);
        this.modelDataMapper = Preconditions.checkNotNull(modelDataMapper);
        this.analytics = Preconditions.checkNotNull(analytics);
    }

    @Override
    public void loadData(Object... params) {
        interactor.execute(new Observer<K>() {
            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

            }

            @Override
            public void onNext(@io.reactivex.annotations.NonNull K o) {
                setModel(modelDataMapper.transform(o));
            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                if (view != null) {
                    view.onError(e.getMessage());
                }
            }

            @Override
            public void onComplete() {

            }
        }, params);
    }

    @Override
    public T getView() {
        return this.view;
    }

    @Override
    public void setView(T view) {
        this.view = view;
    }

    @Override
    public M getModel() {
        return model;
    }

    @Override
    public void setModel(M model) {
        this.model = model;
        if (view != null) {
            view.onComplete(model);
        }
    }

    @Override
    public void stop() {
        setView(null);
    }

    @Override
    public void destroy() {
        interactor.unsubscribe();
        view = null;
    }

    @Override
    public IAnalytics getAnalytics() {
        return analytics;
    }
}
