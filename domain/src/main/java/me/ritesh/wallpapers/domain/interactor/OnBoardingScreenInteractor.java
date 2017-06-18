package me.ritesh.wallpapers.domain.interactor;

import android.support.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

/**
 * @author Ritesh Shakya
 */

public class OnBoardingScreenInteractor extends BaseInteractor {

    @Inject public OnBoardingScreenInteractor(@NonNull Scheduler observerScheduler,
            @NonNull Scheduler subscribeScheduler) {
        super(observerScheduler, subscribeScheduler);
    }

    @Override public Observable buildUseCaseObservable(Object... params) {
        List<Object> entities = new ArrayList<>(params.length);
        Collections.addAll(entities, params);
        return Observable.just(entities);
    }
}
