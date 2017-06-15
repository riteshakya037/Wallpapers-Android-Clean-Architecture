package me.ritesh.wallpapers.adaptors;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import me.ritesh.wallpapers.data.model.objects.OnBoardingData;
import me.ritesh.wallpapers.view.screen.onboarding.OnBoardingPagerFragment;

/**
 * @author Ritesh Shakya
 */

public class OnBoardingAdaptor extends FragmentStatePagerAdapter {

    private List<OnBoardingData> data;

    public OnBoardingAdaptor(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Fragment getItem(int position) {
        return OnBoardingPagerFragment.getInstance(data.get(position));
    }

    public void setData(List<OnBoardingData> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
