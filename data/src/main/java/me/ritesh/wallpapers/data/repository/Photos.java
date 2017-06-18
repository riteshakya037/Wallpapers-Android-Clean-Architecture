package me.ritesh.wallpapers.data.repository;

import android.support.annotation.NonNull;
import io.reactivex.Observable;
import java.util.HashMap;
import java.util.Map;
import me.ritesh.wallpapers.data.net.PixabayApi;
import me.ritesh.wallpapers.data.net.response.objects.PhotosListResponse;

/**
 * @author Ritesh Shakya
 */

public class Photos implements IPhotos {

    private final static String PARAM_KEY = "key";
    private final static String PARAM_IMAGE_TYPE = "image_type";
    private final static String PARAM_PAGE = "page";
    private PixabayApi api;

    public Photos(@NonNull PixabayApi api) {
        this.api = api;
    }

    @Override public Observable<PhotosListResponse> getPhotos(int pageNo) {
        Map<String, String> options = new HashMap<>();
        options.put(PARAM_KEY, "5641489-f5b4d3af9e5cde5b918b871a8");
        options.put(PARAM_PAGE, String.valueOf(pageNo));
        options.put(PARAM_IMAGE_TYPE, "photos");

        return api.getPhotos(options);
    }
}
