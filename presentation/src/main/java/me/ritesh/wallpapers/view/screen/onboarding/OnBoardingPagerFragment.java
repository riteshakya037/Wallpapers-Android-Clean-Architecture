package me.ritesh.wallpapers.view.screen.onboarding;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import javax.inject.Inject;
import me.ritesh.wallpapers.MainApplication;
import me.ritesh.wallpapers.R;
import me.ritesh.wallpapers.data.model.module.OnBoardingPageModule;
import me.ritesh.wallpapers.data.model.objects.OnBoardingData;
import me.ritesh.wallpapers.imageloader.IImageLoader;
import me.ritesh.wallpapers.view.BaseFragment;
import me.ritesh.wallpapers.view.presenter.Presenter;
import org.parceler.Parcels;

/**
 * @author Ritesh Shakya
 */

public class OnBoardingPagerFragment extends BaseFragment<OnBoardingPageModule> {
    private static final String ARG_DATA = "img_data";
    @BindView(R.id.fragment_on_boarding_page_image_view) ImageView mImageView;
    @BindView(R.id.fragment_on_boarding_page_text_headline) TextView mHeadlineText;
    @BindView(R.id.fragment_on_boarding_page_text_subtitle) TextView mSubtitleText;
    @Inject IImageLoader imageLoader;
    @Inject OnBoardingFragmentPresenter onBoardingFragmentPresenter;

    public static Fragment getInstance(OnBoardingData onBoardingData) {
        Fragment fragment = new OnBoardingPagerFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_DATA, Parcels.wrap(onBoardingData));
        fragment.setArguments(args);
        return fragment;
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null && getArguments() != null) {
            onBoardingFragmentPresenter.loadData(
                    Parcels.unwrap(getArguments().getParcelable(ARG_DATA)));
        }
    }

    @Override public void onComplete(OnBoardingPageModule pageModule) {
        OnBoardingData boardingData = pageModule.getOnBoardingData();
        if (boardingData != null) {
            imageLoader.loadImage(boardingData.getImageId(), mImageView);
            mHeadlineText.setText(boardingData.getTitle());
            mSubtitleText.setText(boardingData.getSubtitle());
            mHeadlineText.setTextColor(
                    ContextCompat.getColor(getContext(), boardingData.getTitleColor()));
        }
    }

    @Override protected int getLayoutId() {
        return R.layout.fragment_on_boarding_page;
    }

    @Override protected void injectFragment() {
        ((MainApplication) getActivity().getApplication()).getApplicationComponent().inject(this);
    }

    @Override protected Presenter getPresenter() {
        return onBoardingFragmentPresenter;
    }

    @Override public void onLoading() {

    }

    @Override public void onLoadingMore() {

    }
}
