package me.ritesh.wallpapers.domain.repository;

import io.reactivex.Observable;

/**
 * @author Ritesh Shakya
 */

public interface IComments {
    Observable getComments(String photoId);

    Observable sendComment(String photoId, Object message);
}
