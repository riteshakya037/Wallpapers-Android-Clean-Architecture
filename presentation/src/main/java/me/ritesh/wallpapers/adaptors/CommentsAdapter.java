package me.ritesh.wallpapers.adaptors;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import me.ritesh.wallpapers.R;
import me.ritesh.wallpapers.data.model.objects.CommentsModel;
import me.ritesh.wallpapers.view.screen.comments.CommentPresenter;

/**
 * @author Ritesh Shakya
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.MyViewHolder> {
    private CommentPresenter commentPresenter;

    public CommentsAdapter(CommentPresenter commentPresenter) {
        this.commentPresenter = commentPresenter;
    }

    @Override public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comment_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override public void onBindViewHolder(MyViewHolder holder, int position) {
        CommentsModel comment = commentPresenter.getModel().getComments().get(position);
        holder.setData(comment);
    }

    @Override public int getItemCount() {
        if (commentPresenter.getModel() == null) {
            return 0;
        }
        return commentPresenter.getModel().getComments() == null ? 0
                : commentPresenter.getModel().getComments().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.comment_message) TextView comment;
        @BindView(R.id.comment_username) TextView username;
        @BindView(R.id.comment_date) TextView date;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(CommentsModel comment) {
            this.comment.setText(comment.getComment());
            this.username.setText(comment.getUser());
            this.date.setText(getRelativeTime(comment.getDate()));
        }

        private String getRelativeTime(Date statusTimeStamp) {
            Date currentTime = new Date();
            long timeDiff = (currentTime.getTime() - statusTimeStamp.getTime()) / (60 * 1000);
            if (timeDiff <= 60) {
                return date.getContext()
                        .getResources()
                        .getQuantityString(R.plurals.time_in_minutes, (int) timeDiff,
                                (int) timeDiff);
            } else if (timeDiff <= 60 * 24) {
                return date.getContext()
                        .getResources()
                        .getQuantityString(R.plurals.time_in_hours, (int) timeDiff / 60,
                                (int) timeDiff / 60);
            } else {
                return new SimpleDateFormat("hh:mm aa", Locale.getDefault()).format(
                        statusTimeStamp);
            }
        }
    }
}
