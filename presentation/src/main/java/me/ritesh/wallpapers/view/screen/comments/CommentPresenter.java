package me.ritesh.wallpapers.view.screen.comments;

import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.ritesh.wallpapers.MainApplication;
import me.ritesh.wallpapers.data.analytics.IAnalytics;
import me.ritesh.wallpapers.data.model.module.CommentsModule;
import me.ritesh.wallpapers.data.model.objects.CommentsModel;
import me.ritesh.wallpapers.domain.interactor.CommentsInteractor;
import me.ritesh.wallpapers.view.presenter.Presenter;

/**
 * @author Ritesh Shakya
 */

public class CommentPresenter implements Presenter<CommentsActivityView, CommentsModule> {

    private static final String KEY_USERNAME = "username";
    private MainApplication application;
    private CommentsInteractor interactor;
    private IAnalytics analytics;
    private CommentsModule model;
    private CommentsActivityView view;

    @Inject
    CommentPresenter(@NonNull MainApplication application, @NonNull CommentsInteractor interactor, IAnalytics analytics) {
        this.application = application;
        this.interactor = interactor;
        this.analytics = analytics;
        analytics.LogEventScreen("Comments");
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        view = null;
        interactor.unsubscribe();
    }

    @Override
    public void stop() {

    }

    @Override
    public void loadData(Object... params) {
        final CommentsModule model = new CommentsModule();
        model.setPhotoId(Integer.valueOf(String.valueOf(params[0])));
        setModel(model);

        interactor.execute(new Observer<DataSnapshot>() {
            @Override
            public void onError(Throwable e) {
                if (getView() != null) {
                    getView().onError(e.getMessage());
                }
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

            }

            @Override
            public void onNext(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<Map<String, CommentsModel>> t = new GenericTypeIndicator<Map<String, CommentsModel>>() {
                };

                Map<String, CommentsModel> stringList = dataSnapshot.getValue(t);

                if (stringList != null && stringList.size() > 0) {
                    List<CommentsModel> comments = new ArrayList<>(stringList.values());

                    Collections.sort(comments, new Comparator<CommentsModel>() {
                        @Override
                        public int compare(CommentsModel t1, CommentsModel t2) {
                            return t1.getDate().compareTo(t2.getDate());
                        }
                    });

                    model.setComments(comments);
                }

                if (getView() != null) {
                    getView().onComplete(model);
                }
            }
        }, params[0]);
    }

    @Override
    public CommentsActivityView getView() {
        return view;
    }

    @Override
    public void setView(@NonNull CommentsActivityView view) {
        this.view = view;
    }

    @Override
    public CommentsModule getModel() {
        return model;
    }

    @Override
    public void setModel(CommentsModule model) {
        this.model = model;
    }

    @Override
    public IAnalytics getAnalytics() {
        return analytics;
    }


    void sendComment(CommentsModel message) {
        analytics.LogEventClick("sendComment");
        interactor.sendComment(String.valueOf(getModel().getPhotoId()), message).subscribe();
    }

    void getUserName() {
        String userName = PreferenceManager.getDefaultSharedPreferences(application).getString(KEY_USERNAME, "");
        if (TextUtils.isEmpty(userName)) {
            getView().showDialogBox();
        } else {
            getView().buildComment(userName);
        }
    }

    void saveUserName(String userName) {
        PreferenceManager.getDefaultSharedPreferences(application).edit().putString(KEY_USERNAME, userName).apply();
        getView().buildComment(userName);
    }
}

