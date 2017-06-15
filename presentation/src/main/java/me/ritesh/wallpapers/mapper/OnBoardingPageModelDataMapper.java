package me.ritesh.wallpapers.mapper;

import javax.inject.Inject;

import dagger.internal.Preconditions;
import me.ritesh.wallpapers.data.model.module.OnBoardingPageModule;
import me.ritesh.wallpapers.data.model.objects.OnBoardingData;

/**
 * @author Ritesh Shakya
 */

public class OnBoardingPageModelDataMapper implements IModelDataMapper<OnBoardingData, OnBoardingPageModule> {
    @Inject
    OnBoardingPageModelDataMapper() {
    }

    @Override
    public OnBoardingPageModule transform(OnBoardingData onBoardingData) {
        Preconditions.checkNotNull(onBoardingData);
        OnBoardingPageModule mainScreenModule = new OnBoardingPageModule();
        mainScreenModule.setOnBoardingData(onBoardingData);
        return mainScreenModule;
    }
}
