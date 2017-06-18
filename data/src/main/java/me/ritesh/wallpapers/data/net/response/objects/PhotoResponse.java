package me.ritesh.wallpapers.data.net.response.objects;

import com.google.gson.annotations.Expose;

/**
 * @author Ritesh Shakya
 */
public class PhotoResponse {
    @Expose private int previewHeight;
    @Expose private int likes;
    @Expose private int favorites;
    @Expose private String tags;
    @Expose private int webformatHeight;
    @Expose private int views;
    @Expose private int webformatWidth;
    @Expose private int previewWidth;
    @Expose private int comments;
    @Expose private int downloads;
    @Expose private String pageURL;
    @Expose private String previewURL;
    @Expose private String webformatURL;
    @Expose private int imageWidth;
    @Expose private int user_id;
    @Expose private String user;
    @Expose private String type;
    @Expose private int id;
    @Expose private String userImageURL;
    @Expose private int imageHeight;

    public int getPreviewHeight() {
        return previewHeight;
    }

    public int getLikes() {
        return likes;
    }

    public int getFavorites() {
        return favorites;
    }

    public String getTags() {
        return tags;
    }

    public int getWebformatHeight() {
        return webformatHeight;
    }

    public int getViews() {
        return views;
    }

    public int getWebformatWidth() {
        return webformatWidth;
    }

    public int getPreviewWidth() {
        return previewWidth;
    }

    public int getComments() {
        return comments;
    }

    public int getDownloads() {
        return downloads;
    }

    public String getPageURL() {
        return pageURL;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public String getWebformatURL() {
        return webformatURL;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUser() {
        return user;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getUserImageURL() {
        return userImageURL;
    }

    public int getImageHeight() {
        return imageHeight;
    }
}
