package me.ritesh.wallpapers.data.model.objects;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * @author Ritesh Shakya
 */
@Parcel
public class PhotoModel {
    private int id;
    private String imgSrc;
    private String user;
    private String userImageURL;
//    private ArrayList<String> tags;

    public PhotoModel() {
        // Required by Parcel
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserImageURL() {
        return userImageURL;
    }

    public void setUserImageURL(String userImageURL) {
        this.userImageURL = userImageURL;
    }

//    public ArrayList<String> getTags() {
//        return tags;
//    }
//
//    public void setTags(String[] tags) {
//        ArrayList<String> arrayList = new ArrayList<>(tags.length);
//        for (String tag : arrayList) {
//            arrayList.add(tag);
//        }
//        this.tags = arrayList;
//    }
}
