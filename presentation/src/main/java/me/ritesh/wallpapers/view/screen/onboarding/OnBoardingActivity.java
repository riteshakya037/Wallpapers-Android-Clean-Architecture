package me.ritesh.wallpapers.view.screen.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import me.ritesh.wallpapers.MainApplication;
import me.ritesh.wallpapers.R;
import me.ritesh.wallpapers.adaptors.OnBoardingAdaptor;
import me.ritesh.wallpapers.data.model.module.OnBoardingScreenModule;
import me.ritesh.wallpapers.data.model.objects.OnBoardingData;
import me.ritesh.wallpapers.data.remoteconfig.IRemoteConfig;
import me.ritesh.wallpapers.view.BaseActivity;
import me.ritesh.wallpapers.view.presenter.Presenter;
import me.ritesh.wallpapers.view.screen.photo_listing.PhotosActivity;


public class OnBoardingActivity extends BaseActivity<OnBoardingScreenModule> {
    @Inject
    OnBoardingPresenter onBoardingPresenter;
    @Inject
    IRemoteConfig remoteConfig;
    @BindView(R.id.activity_on_boarding_view_pager)
    ViewPager viewPager;
    @BindView(R.id.activity_on_boarding_dot_tabs)
    TabLayout tabLayout;
    private OnBoardingAdaptor mAdapter;

    @OnClick(R.id.activity_on_boarding_btn_next)
    void openImages() {
        startActivity(new Intent(this, PhotosActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            onBoardingPresenter.loadData(getData());
        }
    }

    private Object[] getData() {
        List<OnBoardingData> entities = new ArrayList<>();
        entities.add(new OnBoardingData("Explore", "Enjoy like never before from  around the globe", R.drawable.temple, R.color.colorOrange));
        entities.add(new OnBoardingData("Amazing", "Places you have never seen before and will never forget", R.drawable.bridge, R.color.colorRed));
        entities.add(new OnBoardingData("Places", "Sites and sounds that will make you smile", R.drawable.pyramids, R.color.colorYellow));
        return entities.toArray();
    }

    @Override
    protected void initViews() {
        mAdapter = new OnBoardingAdaptor(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPager, true);
    }

    @Override
    protected void injectActivity() {
        ((MainApplication) getApplication()).getApplicationComponent().inject(this);
    }


    @Override
    protected Toolbar getToolbarId() {
        return (Toolbar) findViewById(R.id.activity_on_boarding_toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        MenuItem about = menu.findItem(R.id.menu_about);
        switch (remoteConfig.getExperimentVariant(IRemoteConfig.EXPERIMENT_ABOUT_MENU)) {
            case VARIANT_A:
                about.setIcon(R.drawable.ic_info_sign);
                about.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
                break;
            case VARIANT_B:
                about.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
                break;
            default:
                about.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        }

        return true;
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_onboarding;
    }

    @Override
    protected Presenter getPresenter() {
        return onBoardingPresenter;
    }

    @Override
    protected int getCoordinatorLayout() {
        return R.id.activity_on_boarding_coordinator_layout;
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onLoadingMore() {

    }

    @Override
    public void onComplete(OnBoardingScreenModule model) {
        if (model != null) {
            mAdapter.setData(model.getPagerList());
        }
    }
}
