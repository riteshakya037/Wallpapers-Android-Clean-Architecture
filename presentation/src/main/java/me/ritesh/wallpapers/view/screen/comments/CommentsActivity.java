package me.ritesh.wallpapers.view.screen.comments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.OnClick;
import java.util.Date;
import javax.inject.Inject;
import me.ritesh.wallpapers.MainApplication;
import me.ritesh.wallpapers.R;
import me.ritesh.wallpapers.adaptors.CommentsAdapter;
import me.ritesh.wallpapers.data.model.module.CommentsModule;
import me.ritesh.wallpapers.data.model.objects.CommentsModel;
import me.ritesh.wallpapers.data.model.objects.PhotoModel;
import me.ritesh.wallpapers.imageloader.IImageLoader;
import me.ritesh.wallpapers.view.BaseActivity;
import me.ritesh.wallpapers.view.presenter.Presenter;
import org.parceler.Parcels;

public class CommentsActivity extends BaseActivity<CommentsModule>
        implements CommentsActivityView<CommentsModule> {
    public static final String EXTRA_PHOTO = "extra_photo_id";
    @Inject IImageLoader iImageLoader;
    @Inject CommentPresenter commentPresenter;
    @BindView(R.id.activity_comments_list) RecyclerView recyclerView;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.activity_comments_message_input) EditText messageInput;
    @BindView(R.id.activity_comments_image) ImageView ivPhoto;

    CommentsAdapter commentsAdapter;

    @OnClick(R.id.activity_comments_send_button) public void onMessageSend() {
        String message = messageInput.getText().toString();
        if (!message.isEmpty()) {
            commentPresenter.getUserName();
        }
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                PhotoModel model = Parcels.unwrap(bundle.getParcelable(EXTRA_PHOTO));
                iImageLoader.loadImage(model.getImgSrc(), ivPhoto);
                commentPresenter.loadData(String.valueOf(model.getId()));
            } else {
                finish();
            }
        }
    }

    @Override protected void initViews() {
        commentsAdapter = new CommentsAdapter(commentPresenter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(commentsAdapter);
    }

    @Override protected void injectActivity() {
        ((MainApplication) getApplication()).getApplicationComponent().inject(this);
    }

    @Override protected Toolbar getToolbarId() {
        return toolbar;
    }

    @Override protected int getLayout() {
        return R.layout.activity_comments;
    }

    @SuppressWarnings("unchecked") @Override protected Presenter getPresenter() {
        return commentPresenter;
    }

    @Override protected int getCoordinatorLayout() {
        return R.id.root_view;
    }

    @Override public void onLoading() {

    }

    @Override public void onLoadingMore() {

    }

    @Override public void onComplete(CommentsModule model) {
        commentsAdapter.notifyDataSetChanged();
        if (model.getComments() != null) {
            recyclerView.smoothScrollToPosition(model.getComments().size() + 1);
        }
    }

    @Override public void showDialogBox() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        @SuppressLint("InflateParams") View viewInflated =
                LayoutInflater.from(this).inflate(R.layout.dialog_user_name, null, false);
        // Set up the input
        final EditText input = viewInflated.findViewById(R.id.dialog_user_name_input);
        builder.setView(viewInflated);
        builder.setPositiveButton("OK",
                (dialog, which) -> commentPresenter.saveUserName(input.getText().toString()));
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    @Override public void buildComment(String username) {
        String message = messageInput.getText().toString();
        CommentsModel messageModel = new CommentsModel(new Date(), message, username);
        commentPresenter.sendComment(messageModel);
        commentPresenter.getModel().add(messageModel);
        commentsAdapter.notifyDataSetChanged();
        messageInput.setText("");
    }
}
