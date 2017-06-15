package me.ritesh.wallpapers.view.screen.onboarding;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import me.ritesh.wallpapers.data.model.module.OnBoardingPageModule;
import me.ritesh.wallpapers.data.model.objects.OnBoardingData;
import me.ritesh.wallpapers.domain.interactor.OnBoardingPageInteractor;
import me.ritesh.wallpapers.mapper.OnBoardingPageModelDataMapper;
import me.ritesh.wallpapers.view.IView;
import me.ritesh.wallpapers.view.presenter.BasePresenter;

/**
 * @author Ritesh Shakya
 */

class OnBoardingFragmentPresenter extends BasePresenter<IView, OnBoardingPageModule, OnBoardingData> {
    @Inject
    public OnBoardingFragmentPresenter(@NonNull OnBoardingPageInteractor interactor, @NonNull OnBoardingPageModelDataMapper modelDataMapper) {
        super(interactor, modelDataMapper);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }
}
