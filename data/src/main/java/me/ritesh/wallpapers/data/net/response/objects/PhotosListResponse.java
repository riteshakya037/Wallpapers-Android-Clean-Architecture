package me.ritesh.wallpapers.data.net.response.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * @author Ritesh Shakya
 */

public class PhotosListResponse {
    @Expose private int totalHits;

    @SerializedName("hits") private List<PhotoResponse> photoResponseList;

    public List<PhotoResponse> getList() {
        return photoResponseList;
    }
}
