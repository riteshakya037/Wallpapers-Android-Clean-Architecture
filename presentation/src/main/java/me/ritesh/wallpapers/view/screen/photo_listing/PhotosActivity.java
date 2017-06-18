package me.ritesh.wallpapers.view.screen.photo_listing;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.BindView;
import javax.inject.Inject;
import me.ritesh.wallpapers.MainApplication;
import me.ritesh.wallpapers.R;
import me.ritesh.wallpapers.adaptors.PhotosAdapter;
import me.ritesh.wallpapers.data.model.module.PhotosModule;
import me.ritesh.wallpapers.imageloader.IImageLoader;
import me.ritesh.wallpapers.view.BaseActivity;
import me.ritesh.wallpapers.view.presenter.Presenter;

public class PhotosActivity extends BaseActivity<PhotosModule> {
    @Inject PhotosPresenter photosPresenter;
    @Inject IImageLoader imageLoader;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recycler) RecyclerView recyclerView;
    @BindView(R.id.coordinator_layout) CoordinatorLayout coordinatorLayout;
    @BindView(R.id.progress_bar) ProgressBar progressBar;

    PhotosAdapter photosAdapter;

    boolean isFirstTimeCalled = true;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override protected void initViews() {
        photosAdapter = new PhotosAdapter(imageLoader, photosPresenter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(photosAdapter);
        photosPresenter.initPageController(recyclerView);
    }

    @Override protected void onStart() {
        super.onStart();

        if (isFirstTimeCalled) {
            isFirstTimeCalled = false;
            photosPresenter.loadData();
        }
    }

    @Override protected void injectActivity() {
        ((MainApplication) getApplication()).getApplicationComponent().inject(this);
    }

    @Override protected Toolbar getToolbarId() {
        return toolbar;
    }

    @Override protected int getLayout() {
        return R.layout.activity_photos;
    }

    @Override protected Presenter getPresenter() {
        return photosPresenter;
    }

    @Override protected int getCoordinatorLayout() {
        return 0;
    }

    @Override public void onLoading() {
        progressBar.setVisibility(View.VISIBLE);
        coordinatorLayout.setVisibility(View.GONE);
    }

    @Override public void onLoadingMore() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override public void onComplete(PhotosModule model) {
        photosAdapter.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);
        coordinatorLayout.setVisibility(View.VISIBLE);
    }
}
