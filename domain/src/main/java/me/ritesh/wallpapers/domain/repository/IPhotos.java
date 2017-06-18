package me.ritesh.wallpapers.domain.repository;

import io.reactivex.Observable;

/**
 * @author Ritesh Shakya
 */

public interface IPhotos {
    Observable getPhotos(int pageNo);
}
