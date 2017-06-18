package me.ritesh.wallpapers.domain.interactor;

import android.support.annotation.NonNull;
import dagger.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import javax.inject.Inject;
import me.ritesh.wallpapers.data.repository.IPhotos;

/**
 * @author Ritesh Shakya
 */

public class ImagePageInteractor extends BaseInteractor {

    @NonNull private final IPhotos iPhotos;

    @Inject
    public ImagePageInteractor(@NonNull IPhotos iPhotos, @NonNull Scheduler observerScheduler,
            @NonNull Scheduler subscribeScheduler) {
        super(observerScheduler, subscribeScheduler);
        this.iPhotos = iPhotos;
    }

    @Override public Observable buildUseCaseObservable(Object... params) {
        Preconditions.checkNotNull(params);
        return iPhotos.getPhotos((int) params[0]);
    }
}
