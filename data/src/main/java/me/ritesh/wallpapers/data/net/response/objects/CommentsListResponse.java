package me.ritesh.wallpapers.data.net.response.objects;

import java.util.List;
import me.ritesh.wallpapers.data.model.objects.CommentsModel;

/**
 * @author Ritesh Shakya
 */

public class CommentsListResponse {
    private List<CommentsModel> photoResponseList;

    public List<CommentsModel> getList() {
        return photoResponseList;
    }
}
