package me.ritesh.wallpapers.view.screen.photo_listing;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import me.ritesh.wallpapers.data.analytics.IAnalytics;
import me.ritesh.wallpapers.data.model.module.PhotosModule;
import me.ritesh.wallpapers.data.net.response.objects.PhotosListResponse;
import me.ritesh.wallpapers.domain.interactor.ImagePageInteractor;
import me.ritesh.wallpapers.mapper.PhotosDataMapper;
import me.ritesh.wallpapers.view.IView;
import me.ritesh.wallpapers.view.presenter.PaginatedBasePresenter;

/**
 * @author Ritesh Shakya
 */

public class PhotosPresenter extends PaginatedBasePresenter<IView, PhotosModule, PhotosListResponse> {
    @Inject
    protected PhotosPresenter(@NonNull ImagePageInteractor interactor, @NonNull PhotosDataMapper modelDataMapper, IAnalytics analytics) {
        super(interactor, modelDataMapper, analytics);
        analytics.LogEventScreen("PhotosScreen");
    }

    @Override
    protected void pagingAddNewData(PhotosModule newModel) {
        getModel().getPhotoModels().addAll(newModel.getPhotoModels());
    }

    @Override
    public void loadData(Object... params) {
        super.loadData(getModel() == null ? 1 : getModel().getPageNumber());
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void onNewPageRequest(int lastItemIndex) {
        PhotosModule model = getModel();
        synchronized (getModel()) {
            if (getModel().getLastItemIndex() < lastItemIndex) {
                model.setLastItemIndex(lastItemIndex);
                model.setPageNumber(model.getPageNumber() + 1);
                loadData();
            }
        }
    }

}
