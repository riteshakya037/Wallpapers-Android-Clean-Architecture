package me.ritesh.wallpapers.mapper;

import dagger.internal.Preconditions;
import javax.inject.Inject;
import me.ritesh.wallpapers.model.module.OnBoardingPageModule;
import me.ritesh.wallpapers.data.model.OnBoardingData;

/**
 * @author Ritesh Shakya
 */

public class OnBoardingPageModelDataMapper
        implements IModelDataMapper<OnBoardingData, OnBoardingPageModule> {
    @Inject OnBoardingPageModelDataMapper() {
    }

    @Override public OnBoardingPageModule transform(OnBoardingData onBoardingData) {
        Preconditions.checkNotNull(onBoardingData);
        OnBoardingPageModule mainScreenModule = new OnBoardingPageModule();
        mainScreenModule.setOnBoardingData(onBoardingData);
        return mainScreenModule;
    }
}
