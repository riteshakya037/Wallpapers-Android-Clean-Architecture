package me.ritesh.wallpapers.data.model.objects;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;

import org.parceler.Parcel;

/**
 * @author Ritesh Shakya
 */
@Parcel
public class OnBoardingData {
    private String title;
    private int titleColor;
    private String subtitle;
    private int imageId;

    public OnBoardingData() {
        // Required by Parcel
    }

    public OnBoardingData(String title, String subtitle, @DrawableRes int imageId, @ColorRes int titleColor) {
        this.title = title;
        this.subtitle = subtitle;
        this.imageId = imageId;
        this.titleColor = titleColor;
    }

    public int getImageId() {
        return imageId;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getTitle() {
        return title;
    }

    public int getTitleColor() {
        return titleColor;
    }
}
