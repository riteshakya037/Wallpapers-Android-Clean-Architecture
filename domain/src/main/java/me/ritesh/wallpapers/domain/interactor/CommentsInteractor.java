package me.ritesh.wallpapers.domain.interactor;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import me.ritesh.wallpapers.data.model.objects.CommentsModel;
import me.ritesh.wallpapers.data.repository.IComments;

/**
 * @author Ritesh Shakya
 */

public class CommentsInteractor extends BaseInteractor {

    private IComments comments;

    @Inject
    public CommentsInteractor(@NonNull IComments comments,
                              @NonNull Scheduler observerScheduler, @NonNull Scheduler subscribeScheduler) {
        super(observerScheduler, subscribeScheduler);
        this.comments = comments;
    }

    @Override
    protected Observable buildUseCaseObservable(Object... params) {
        return comments.getComments((String) params[0]);
    }

    public Observable sendComment(String photoId, CommentsModel message) {
        return comments.sendComment(photoId, message);
    }
}
