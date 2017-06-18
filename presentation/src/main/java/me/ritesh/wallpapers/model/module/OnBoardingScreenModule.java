package me.ritesh.wallpapers.model.module;

import java.util.ArrayList;
import java.util.List;
import me.ritesh.wallpapers.data.model.OnBoardingData;
import org.parceler.Parcel;

/**
 * @author Ritesh Shakya
 */
@Parcel public class OnBoardingScreenModule {
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
