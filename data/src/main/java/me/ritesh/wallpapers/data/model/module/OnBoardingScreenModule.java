package me.ritesh.wallpapers.data.model.module;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

import me.ritesh.wallpapers.data.model.objects.OnBoardingData;

/**
 * @author Ritesh Shakya
 */
@Parcel
public class OnBoardingScreenModule {
    List<OnBoardingData> pagerList = new ArrayList<>();

    public List<OnBoardingData> getPagerList() {
        return pagerList;
    }

    public void setPagerList(List<OnBoardingData> pagerList) {
        this.pagerList = pagerList;
    }

    public void setAddItem(OnBoardingData addItem) {
        pagerList.add(addItem);
    }
}
