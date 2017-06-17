package me.ritesh.wallpapers.domain.interactor;

import android.support.annotation.NonNull;

import dagger.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;

/**
 * @author Ritesh Shakya
 */

public abstract class BaseInteractor implements Interactor {

    private final Scheduler observerScheduler;
    private final Scheduler subscribeScheduler;
    private Observable observable = Observable.empty();

    BaseInteractor(@NonNull Scheduler observerScheduler, @NonNull Scheduler subscribeScheduler) {
        this.observerScheduler = Preconditions.checkNotNull(observerScheduler);
        this.subscribeScheduler = Preconditions.checkNotNull(subscribeScheduler);
    }

    @Override
    public void execute(@NonNull Observer useCaseSubscriber, Object... params) {
        observable = this.buildUseCaseObservable(params)
                .subscribeOn(subscribeScheduler)
                .observeOn(observerScheduler);
        observable.subscribe(useCaseSubscriber);
    }

    protected abstract Observable buildUseCaseObservable(Object... params);

    @Override
    public void unsubscribe() {
        if (observable != Observable.empty())
            observable.unsubscribeOn(observerScheduler);
    }
}
