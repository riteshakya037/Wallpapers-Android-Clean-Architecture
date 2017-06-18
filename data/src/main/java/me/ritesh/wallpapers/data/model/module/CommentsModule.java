package me.ritesh.wallpapers.data.model.module;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

import me.ritesh.wallpapers.data.model.objects.CommentsModel;

/**
 * @author Ritesh Shakya
 */
@Parcel
public class CommentsModule {
    List<CommentsModel> commentsList = new ArrayList<>();
    int photoId;
    String imageUrl;

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public List<CommentsModel> getComments() {
        return commentsList;
    }

    public void setComments(List<CommentsModel> comments) {
        commentsList = new ArrayList<>(comments);
    }

    public void add(CommentsModel messageModel) {
        if (!commentsList.contains(messageModel))
            this.commentsList.add(messageModel);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
