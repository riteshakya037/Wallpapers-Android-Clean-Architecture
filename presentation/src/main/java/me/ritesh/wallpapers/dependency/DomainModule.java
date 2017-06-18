package me.ritesh.wallpapers.dependency;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import javax.inject.Named;
import me.ritesh.wallpapers.data.repository.IComments;
import me.ritesh.wallpapers.data.repository.IPhotos;
import me.ritesh.wallpapers.domain.interactor.CommentsInteractor;
import me.ritesh.wallpapers.domain.interactor.ImagePageInteractor;
import me.ritesh.wallpapers.domain.interactor.OnBoardingPageInteractor;
import me.ritesh.wallpapers.domain.interactor.OnBoardingScreenInteractor;

/**
 * @author Ritesh Shakya
 */
@Module public class DomainModule {
    @Provides OnBoardingScreenInteractor provideMainScreenInteractor(
            @Named(Rx.MAIN) Scheduler observerScheduler,
            @Named(Rx.IO) Scheduler subscribeScheduler) {
        return new OnBoardingScreenInteractor(observerScheduler, subscribeScheduler);
    }

    @Provides OnBoardingPageInteractor provideMainPageInteractor(
            @Named(Rx.MAIN) Scheduler observerScheduler,
            @Named(Rx.IO) Scheduler subscribeScheduler) {
        return new OnBoardingPageInteractor(observerScheduler, subscribeScheduler);
    }

    @Provides ImagePageInteractor providesImagePageInteractor(IPhotos iPhotos,
            @Named(Rx.MAIN) Scheduler mainScheduler, @Named(Rx.IO) Scheduler ioScheduler) {
        return new ImagePageInteractor(iPhotos, mainScheduler, ioScheduler);
    }

    @Provides CommentsInteractor providesCommentsInteractor(IComments comments,
            @Named(Rx.MAIN) Scheduler mainScheduler, @Named(Rx.IO) Scheduler ioScheduler) {
        return new CommentsInteractor(comments, mainScheduler, ioScheduler);
    }
}
