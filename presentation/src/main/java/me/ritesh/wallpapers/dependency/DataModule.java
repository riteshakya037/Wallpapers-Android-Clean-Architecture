package me.ritesh.wallpapers.dependency;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import javax.inject.Singleton;
import me.ritesh.wallpapers.BuildConfig;
import me.ritesh.wallpapers.MainApplication;
import me.ritesh.wallpapers.data.analytics.FirebaseTracker;
import me.ritesh.wallpapers.data.analytics.IAnalytics;
import me.ritesh.wallpapers.data.model.objects.CommentsModel;
import me.ritesh.wallpapers.data.net.PixabayApi;
import me.ritesh.wallpapers.data.remoteconfig.IRemoteConfig;
import me.ritesh.wallpapers.data.remoteconfig.RemoteConfig;
import me.ritesh.wallpapers.data.repository.Comments;
import me.ritesh.wallpapers.data.repository.IComments;
import me.ritesh.wallpapers.data.repository.IPhotos;
import me.ritesh.wallpapers.data.repository.Photos;

/**
 * @author Ritesh Shakya
 */
@Module public class DataModule {
    private final MainApplication application;

    public DataModule(MainApplication mainApplication) {
        this.application = mainApplication;
    }

    @Provides MainApplication providesMainApplication() {
        return application;
    }

    @Singleton @Provides IAnalytics provideTracking(MainApplication application) {
        int playServicesStatus =
                GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(application);
        if (playServicesStatus == ConnectionResult.SUCCESS) {
            return new FirebaseTracker(FirebaseAnalytics.getInstance(application));
        } else {
            return new IAnalytics() {
                @Override public void LogEventScreen(String eventName) {
                }

                @Override public void LogEventClick(String eventName) {
                }

                @Override public void LogEvent(String category, String eventName) {
                }

                @Override public void LogException(String exception) {
                }

                @Override public void setUserProperty(String experimentName,
                        IRemoteConfig.ExperimentVariant experimentVariant) {

                }
            };
        }
    }

    @Singleton @Provides IRemoteConfig provideRemoteConfig(MainApplication application,
            IAnalytics analytics) {
        int playServicesStatus =
                GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(application);
        if (playServicesStatus == ConnectionResult.SUCCESS) {
            return new RemoteConfig(BuildConfig.DEBUG, analytics);
        } else {
            return key -> IRemoteConfig.ExperimentVariant.NONE;
        }
    }

    @Singleton @Provides IComments provideComments(MainApplication application) {
        int playServicesStatus =
                GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(application);
        if (playServicesStatus == ConnectionResult.SUCCESS) {
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            firebaseDatabase.setPersistenceEnabled(true);
            DatabaseReference databaseReference = firebaseDatabase.getReference();
            return new Comments(databaseReference);
        } else {
            return new IComments() {
                @Override public Observable getComments(String photoId) {
                    return Observable.error(new RuntimeException("FireBase not supported"));
                }

                @Override public Observable sendComment(String photoId, CommentsModel message) {
                    return Observable.error(new RuntimeException("FireBase not supported"));
                }
            };
        }
    }

    @Provides IPhotos providePhotos(PixabayApi pixabayApi) {
        return new Photos(pixabayApi);
    }
}
