package me.ritesh.wallpapers.data.model.module;

import java.util.ArrayList;
import java.util.List;
import me.ritesh.wallpapers.data.model.objects.PhotoModel;
import org.parceler.Parcel;

/**
 * @author Ritesh Shakya
 */
@Parcel public class PhotosModule {
    List<PhotoModel> photoModels = new ArrayList<>();

    private int pageNumber = 1;
    private int lastItemIndex = 0;

    public int getLastItemIndex() {
        return lastItemIndex;
    }

    public void setLastItemIndex(int lastItemIndex) {
        this.lastItemIndex = lastItemIndex;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<PhotoModel> getPhotoModels() {
        return photoModels;
    }

    public void addPhotoModel(PhotoModel photoModels) {
        this.photoModels.add(photoModels);
    }
}
