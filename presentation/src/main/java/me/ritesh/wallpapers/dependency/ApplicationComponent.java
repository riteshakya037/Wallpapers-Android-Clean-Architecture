package me.ritesh.wallpapers.dependency;

import javax.inject.Singleton;

import dagger.Component;
import me.ritesh.wallpapers.MainApplication;
import me.ritesh.wallpapers.view.screen.onboarding.OnBoardingActivity;
import me.ritesh.wallpapers.view.screen.onboarding.OnBoardingPagerFragment;

/**
 * @author Ritesh Shakya
 */

@Singleton
@Component(
        modules = {
                DataModule.class,
                DomainModule.class,
                Rx.class,
                ImageModule.class
        }
)
public interface ApplicationComponent {
    void inject(MainApplication mainApplication);

    void inject(OnBoardingActivity onBoardingActivity);

    void inject(OnBoardingPagerFragment onBoardingPagerFragment);
}
