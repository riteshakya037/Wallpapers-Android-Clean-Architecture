package me.ritesh.wallpapers.data.model.module;

import me.ritesh.wallpapers.data.model.objects.OnBoardingData;
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
