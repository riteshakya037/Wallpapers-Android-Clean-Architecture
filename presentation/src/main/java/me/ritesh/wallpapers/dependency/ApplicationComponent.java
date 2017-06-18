package me.ritesh.wallpapers.dependency;

import dagger.Component;
import javax.inject.Singleton;
import me.ritesh.wallpapers.MainApplication;
import me.ritesh.wallpapers.view.screen.comments.CommentsActivity;
import me.ritesh.wallpapers.view.screen.onboarding.OnBoardingActivity;
import me.ritesh.wallpapers.view.screen.onboarding.OnBoardingPagerFragment;
import me.ritesh.wallpapers.view.screen.photo_listing.PhotosActivity;
import me.ritesh.wallpapers.view.screen.splash.SplashActivity;

/**
 * @author Ritesh Shakya
 */

@Singleton @Component(modules = {
        NetworkModule.class, DataModule.class, DomainModule.class, Rx.class, ImageModule.class
}) public interface ApplicationComponent {
    void inject(MainApplication mainApplication);

    void inject(OnBoardingActivity onBoardingActivity);

    void inject(OnBoardingPagerFragment onBoardingPagerFragment);

    void inject(SplashActivity splashActivity);

    void inject(PhotosActivity photosActivity);

    void inject(CommentsActivity commentsActivity);
}
