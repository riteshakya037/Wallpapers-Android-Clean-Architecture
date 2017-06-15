package me.ritesh.wallpapers.data.model.module;

import org.parceler.Parcel;

import me.ritesh.wallpapers.data.model.objects.OnBoardingData;

/**
 * @author Ritesh Shakya
 */
@Parcel
public class OnBoardingPageModule {
    OnBoardingData onBoardingData = new OnBoardingData();

    public OnBoardingData getOnBoardingData() {
        return onBoardingData;
    }

    public void setOnBoardingData(OnBoardingData onBoardingData) {
        this.onBoardingData = onBoardingData;
    }
}
