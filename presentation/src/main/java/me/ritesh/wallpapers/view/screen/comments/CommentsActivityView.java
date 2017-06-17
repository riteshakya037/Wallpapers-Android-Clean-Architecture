package me.ritesh.wallpapers.view.screen.comments;

import me.ritesh.wallpapers.view.IView;

/**
 * @author Ritesh Shakya
 */

interface CommentsActivityView<T> extends IView<T> {
    void showDialogBox();

    void buildComment(String username);
}
