package me.ritesh.wallpapers.model.module;

import me.ritesh.wallpapers.data.model.OnBoardingData;
import org.parceler.Parcel;

/**
 * @author Ritesh Shakya
 */
@Parcel public class OnBoardingPageModule {
    OnBoardingData onBoardingData = new OnBoardingData();

    public OnBoardingData getOnBoardingData() {
        return onBoardingData;
    }

    public void setOnBoardingData(OnBoardingData onBoardingData) {
        this.onBoardingData = onBoardingData;
    }
}
