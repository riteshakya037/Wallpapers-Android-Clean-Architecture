package me.ritesh.wallpapers.dependency;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import me.ritesh.wallpapers.domain.interactor.OnBoardingPageInteractor;
import me.ritesh.wallpapers.domain.interactor.OnBoardingScreenInteractor;

/**
 * @author Ritesh Shakya
 */
@Module
public class DomainModule {
    @Provides
    OnBoardingScreenInteractor provideMainScreenInteractor(@Named(Rx.MAIN) Scheduler observerScheduler,
                                                           @Named(Rx.IO) Scheduler subscribeScheduler) {
        return new OnBoardingScreenInteractor(observerScheduler, subscribeScheduler);
    }

    @Provides
    OnBoardingPageInteractor provideMainPageInteractor(@Named(Rx.MAIN) Scheduler observerScheduler,
                                                       @Named(Rx.IO) Scheduler subscribeScheduler) {
        return new OnBoardingPageInteractor(observerScheduler, subscribeScheduler);
    }
}
