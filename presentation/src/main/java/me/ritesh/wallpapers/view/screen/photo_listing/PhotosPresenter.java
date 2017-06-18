package me.ritesh.wallpapers.view.screen.photo_listing;

import android.os.Bundle;
import android.support.annotation.NonNull;
import io.reactivex.Observer;
import javax.inject.Inject;
import me.ritesh.wallpapers.data.analytics.IAnalytics;
import me.ritesh.wallpapers.data.model.module.PhotosModule;
import me.ritesh.wallpapers.data.model.objects.PhotoModel;
import me.ritesh.wallpapers.data.net.response.objects.PhotosListResponse;
import me.ritesh.wallpapers.domain.interactor.CommentsInteractor;
import me.ritesh.wallpapers.domain.interactor.ImagePageInteractor;
import me.ritesh.wallpapers.mapper.PhotosDataMapper;
import me.ritesh.wallpapers.view.IView;
import me.ritesh.wallpapers.view.presenter.PaginatedBasePresenter;
import me.ritesh.wallpapers.view.screen.comments.CommentsActivity;
import org.parceler.Parcels;

/**
 * @author Ritesh Shakya
 */

public class PhotosPresenter
        extends PaginatedBasePresenter<IView, PhotosModule, PhotosListResponse> {
    private CommentsInteractor commentsInteractor;

    @Inject protected PhotosPresenter(@NonNull ImagePageInteractor interactor,
            @NonNull PhotosDataMapper modelDataMapper, CommentsInteractor commentsInteractor,
            IAnalytics analytics) {
        super(interactor, modelDataMapper, analytics);
        this.commentsInteractor = commentsInteractor;
        analytics.LogEventScreen("PhotosScreen");
    }

    @Override protected void pagingAddNewData(PhotosModule newModel) {
        getModel().getPhotoModels().addAll(newModel.getPhotoModels());
    }

    @Override public void loadData(Object... params) {
        super.loadData(getModel() == null ? 1 : getModel().getPageNumber());
    }

    @Override public void resume() {

    }

    @Override public void pause() {

    }

    @Override public void onNewPageRequest(int lastItemIndex) {
        PhotosModule model = getModel();
        synchronized (getModel()) {
            if (getModel().getLastItemIndex() < lastItemIndex) {
                model.setLastItemIndex(lastItemIndex);
                model.setPageNumber(model.getPageNumber() + 1);
                loadData();
            }
        }
    }

    public void onCommentClick(PhotoModel model) {
        getAnalytics().LogEventClick("onCommentClick - " + model.getId());

        if (getView() != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(CommentsActivity.EXTRA_PHOTO, Parcels.wrap(model));

            getView().onStartActivity(CommentsActivity.class, bundle);
        }
    }

    public void getComments(Observer subscriber, String photoId) {
        commentsInteractor.execute(subscriber, photoId);
    }
}
