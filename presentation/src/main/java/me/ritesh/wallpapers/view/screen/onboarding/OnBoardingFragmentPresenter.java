package me.ritesh.wallpapers.view.screen.onboarding;

import android.support.annotation.NonNull;
import javax.inject.Inject;
import me.ritesh.wallpapers.domain.analytics.IAnalytics;
import me.ritesh.wallpapers.model.module.OnBoardingPageModule;
import me.ritesh.wallpapers.data.model.OnBoardingData;
import me.ritesh.wallpapers.domain.interactor.OnBoardingPageInteractor;
import me.ritesh.wallpapers.mapper.OnBoardingPageModelDataMapper;
import me.ritesh.wallpapers.view.IView;
import me.ritesh.wallpapers.view.presenter.BasePresenter;

/**
 * @author Ritesh Shakya
 */

class OnBoardingFragmentPresenter
        extends BasePresenter<IView, OnBoardingPageModule, OnBoardingData> {
    @Inject OnBoardingFragmentPresenter(@NonNull OnBoardingPageInteractor interactor,
            @NonNull OnBoardingPageModelDataMapper modelDataMapper, IAnalytics analytics) {
        super(interactor, modelDataMapper, analytics);
    }
}
