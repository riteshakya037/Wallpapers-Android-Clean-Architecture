package me.ritesh.wallpapers.domain.interactor;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * @author Ritesh Shakya
 */

public class OnBoardingPageInteractor extends BaseInteractor {

    @Inject
    public OnBoardingPageInteractor(@NonNull Scheduler observerScheduler, @NonNull Scheduler subscribeScheduler) {
        super(observerScheduler, subscribeScheduler);
    }

    @Override
    public Observable buildUseCaseObservable(Object... params) {
        return Observable.just(params[0]);
    }
}
