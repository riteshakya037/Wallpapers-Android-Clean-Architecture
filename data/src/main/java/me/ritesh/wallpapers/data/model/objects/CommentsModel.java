package me.ritesh.wallpapers.data.model.objects;

import java.util.Date;
import org.parceler.Parcel;

/**
 * @author Ritesh Shakya
 */

@Parcel public class CommentsModel {
    Date date;
    String comment;
    String user;

    public CommentsModel() {
    }

    public CommentsModel(Date date, String comment, String username) {
        this.date = date;
        this.comment = comment;
        this.user = username;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentsModel that = (CommentsModel) o;

        return date.equals(that.date) && comment.equals(that.comment);
    }

    @Override public int hashCode() {
        int result = date.hashCode();
        result = 31 * result + comment.hashCode();
        return result;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}