package me.ritesh.wallpapers.domain.interactor;

import android.support.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import javax.inject.Inject;

/**
 * @author Ritesh Shakya
 */

public class OnBoardingPageInteractor extends BaseInteractor {

    @Inject public OnBoardingPageInteractor(@NonNull Scheduler observerScheduler,
            @NonNull Scheduler subscribeScheduler) {
        super(observerScheduler, subscribeScheduler);
    }

    @Override public Observable buildUseCaseObservable(Object... params) {
        return Observable.just(params[0]);
    }
}
