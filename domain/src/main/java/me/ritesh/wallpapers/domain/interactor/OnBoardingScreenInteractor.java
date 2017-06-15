package me.ritesh.wallpapers.domain.interactor;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import me.ritesh.wallpapers.data.model.objects.OnBoardingData;

/**
 * @author Ritesh Shakya
 */

public class OnBoardingScreenInteractor extends BaseInteractor {

    @Inject
    public OnBoardingScreenInteractor(@NonNull Scheduler observerScheduler, @NonNull Scheduler subscribeScheduler) {
        super(observerScheduler, subscribeScheduler);
    }

    @Override
    public Observable buildUseCaseObservable(Object... params) {
        List<OnBoardingData> entities = new ArrayList<>(params.length);
        for (Object o : params) {
            if (o instanceof OnBoardingData)
                entities.add((OnBoardingData) o);
        }
        return Observable.just(entities);
    }
}
