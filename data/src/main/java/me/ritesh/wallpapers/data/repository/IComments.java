package me.ritesh.wallpapers.data.repository;

import io.reactivex.Observable;
import me.ritesh.wallpapers.data.model.objects.CommentsModel;

/**
 * @author Ritesh Shakya
 */

public interface IComments {
    Observable getComments(String photoId);

    Observable sendComment(String photoId, CommentsModel message);
}
