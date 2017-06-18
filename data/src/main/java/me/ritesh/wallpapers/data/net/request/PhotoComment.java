package me.ritesh.wallpapers.data.net.request;

import java.util.Date;
import org.parceler.Parcel;

/**
 * @author Ritesh Shakya
 */

@Parcel class PhotoComment {
    private Date date;
    private String comment;

    PhotoComment() {
        //        Required by Parcel
    }

    public PhotoComment(Date date, String comment) {
        this.date = date;
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
