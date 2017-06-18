package me.ritesh.wallpapers.view.presenter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.ritesh.wallpapers.data.analytics.IAnalytics;
import me.ritesh.wallpapers.domain.interactor.BaseInteractor;
import me.ritesh.wallpapers.mapper.IModelDataMapper;
import me.ritesh.wallpapers.utils.PageController;
import me.ritesh.wallpapers.view.IView;

/**
 * @author Ritesh Shakya
 */

public abstract class PaginatedBasePresenter<T extends IView, M, K> extends BasePresenter<T, M, K>
        implements PageController.OnNewPageRequest {
    @NonNull private final BaseInteractor interactor;
    @NonNull private final IModelDataMapper<K, M> modelDataMapper;
    private final IAnalytics analytics;
    PageController pageController;

    protected PaginatedBasePresenter(@NonNull BaseInteractor interactor,
            @NonNull IModelDataMapper<K, M> modelDataMapper, IAnalytics analytics) {
        super(interactor, modelDataMapper, analytics);
        this.interactor = interactor;
        this.modelDataMapper = modelDataMapper;
        this.analytics = analytics;
    }

    public PageController getPageController() {
        return pageController;
    }

    public void initPageController(RecyclerView recyclerView) {
        this.pageController = new PageController(recyclerView, this);
    }

    @Override public void loadData(Object... params) {
        if (getView() != null) {
            if (getModel() != null) {
                getView().onLoadingMore();
            } else {
                getView().onLoading();
            }
        }
        interactor.execute(new Observer<K>() {
            @Override public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

            }

            @Override public void onNext(@io.reactivex.annotations.NonNull K o) {
                M tempMode = modelDataMapper.transform(o);
                pageController.setOnLoadFinish(true);
                if (getModel() == null) {
                    setModel(tempMode);
                } else {
                    pagingAddNewData(tempMode);
                    if (getView() != null) {
                        getView().onComplete(tempMode);
                    }
                }
            }

            @Override public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                if (getView() != null) {
                    getView().onError(e.getMessage());
                }
            }

            @Override public void onComplete() {

            }
        }, params);
    }

    protected abstract void pagingAddNewData(M newModel);

    @Override public void resume() {

    }

    @Override public void pause() {

    }

    @Override public void onNewPageRequest(int lastItemIndex) {

    }
}
