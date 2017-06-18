package me.ritesh.wallpapers.utils;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class PageController extends RecyclerView.OnScrollListener {
    private final String TAG = PageController.class.getSimpleName();
    private GridLayoutManager layoutManager;
    private OnNewPageRequest listener;
    private boolean loading = true;

    public PageController(RecyclerView recyclerView, OnNewPageRequest listener) {
        if (!(recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
            throw new UnsupportedOperationException("support only GridLayoutManager");
        }

        this.layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        this.listener = listener;

        recyclerView.addOnScrollListener(this);
    }

    public void setOnLoadFinish(boolean isLoadFinish) {
        loading = isLoadFinish;
    }

    @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (dy > 0) //check for scroll down
        {
            if (layoutManager == null) {
                throw new NullPointerException("LayoutManager not defined");
            }

            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

            if (loading) {
                if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                    loading = false;

                    listener.onNewPageRequest(totalItemCount);
                }
            }
        }
    }

    public interface OnNewPageRequest {
        void onNewPageRequest(int lastItemIndex);
    }
}

