package me.ritesh.wallpapers.dependency;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.analytics.FirebaseAnalytics;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.ritesh.wallpapers.BuildConfig;
import me.ritesh.wallpapers.MainApplication;
import me.ritesh.wallpapers.data.analytics.FirebaseTracker;
import me.ritesh.wallpapers.data.analytics.IAnalytics;
import me.ritesh.wallpapers.data.net.PixabayApi;
import me.ritesh.wallpapers.data.remoteconfig.IRemoteConfig;
import me.ritesh.wallpapers.data.remoteconfig.RemoteConfig;
import me.ritesh.wallpapers.data.repository.IPhotos;
import me.ritesh.wallpapers.data.repository.Photos;

/**
 * @author Ritesh Shakya
 */
@Module
public class DataModule {
    private final MainApplication application;

    public DataModule(MainApplication mainApplication) {
        this.application = mainApplication;
    }

    @Provides
    MainApplication providesMainApplication() {
        return application;
    }

    @Singleton
    @Provides
    IAnalytics provideTracking(MainApplication application) {
        int playServicesStatus = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(application);
        if (playServicesStatus == ConnectionResult.SUCCESS) {
            return new FirebaseTracker(FirebaseAnalytics.getInstance(application));
        } else {
            return new IAnalytics() {
                @Override
                public void LogEventScreen(String eventName) {
                }

                @Override
                public void LogEventClick(String eventName) {
                }

                @Override
                public void LogEvent(String category, String eventName) {
                }

                @Override
                public void LogException(String exception) {
                }

                @Override
                public void setUserProperty(String experimentName, IRemoteConfig.ExperimentVariant experimentVariant) {

                }
            };
        }
    }

    @Singleton
    @Provides
    IRemoteConfig provideRemoteConfig(MainApplication application, IAnalytics analytics) {
        int playServicesStatus = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(application);
        if (playServicesStatus == ConnectionResult.SUCCESS) {
            return new RemoteConfig(BuildConfig.DEBUG, analytics);
        } else {
            return new IRemoteConfig() {

                @Override
                public ExperimentVariant getExperimentVariant(String key) {
                    return ExperimentVariant.NONE;
                }
            };
        }
    }

    @Provides
    IPhotos provideMarsPhotos(PixabayApi pixabayApi) {
        return new Photos(pixabayApi);
    }
}
