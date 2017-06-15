package me.ritesh.wallpapers.view.screen.onboarding;

import java.util.List;

import javax.inject.Inject;

import me.ritesh.wallpapers.data.model.module.OnBoardingScreenModule;
import me.ritesh.wallpapers.data.model.objects.OnBoardingData;
import me.ritesh.wallpapers.data.analytics.IAnalytics;
import me.ritesh.wallpapers.domain.interactor.OnBoardingScreenInteractor;
import me.ritesh.wallpapers.mapper.OnBoardingScreenModelDataMapper;
import me.ritesh.wallpapers.view.IView;
import me.ritesh.wallpapers.view.presenter.BasePresenter;

/**
 * @author Ritesh Shakya
 */

class OnBoardingPresenter extends BasePresenter<IView, OnBoardingScreenModule, List<OnBoardingData>> {
    @Inject
    public OnBoardingPresenter(OnBoardingScreenInteractor interactor, OnBoardingScreenModelDataMapper modelDataMapper, IAnalytics analytics) {
        super(interactor, modelDataMapper, analytics);
        analytics.LogEventScreen("OnBoarding");
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

}
