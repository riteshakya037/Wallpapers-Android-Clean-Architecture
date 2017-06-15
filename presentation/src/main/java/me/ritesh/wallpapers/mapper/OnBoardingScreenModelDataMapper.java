package me.ritesh.wallpapers.mapper;

import java.util.List;

import javax.inject.Inject;

import dagger.internal.Preconditions;
import me.ritesh.wallpapers.data.model.module.OnBoardingScreenModule;
import me.ritesh.wallpapers.data.model.objects.OnBoardingData;

/**
 * @author Ritesh Shakya
 */

public class OnBoardingScreenModelDataMapper implements IModelDataMapper<List<OnBoardingData>, OnBoardingScreenModule> {
    @Inject
    OnBoardingScreenModelDataMapper() {
    }

    @Override
    public OnBoardingScreenModule transform(List<OnBoardingData> dataList) {
        Preconditions.checkNotNull(dataList);
        OnBoardingScreenModule mainScreenModule = new OnBoardingScreenModule();
        mainScreenModule.setPagerList(dataList);
        return mainScreenModule;
    }
}
