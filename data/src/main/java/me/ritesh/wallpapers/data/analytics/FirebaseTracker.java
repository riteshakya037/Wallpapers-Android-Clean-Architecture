package me.ritesh.wallpapers.data.analytics;

import android.os.Bundle;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crash.FirebaseCrash;
import me.ritesh.wallpapers.data.remoteconfig.IRemoteConfig;

/**
 * @author Ritesh Shakya
 */

public class FirebaseTracker implements IAnalytics {
    private static final String CATEGORY_SCREEN = "screen";
    private static final String CATEGORY_CLICK = "click";

    private final FirebaseAnalytics firebaseAnalytics;

    public FirebaseTracker(FirebaseAnalytics firebaseAnalytics) {
        this.firebaseAnalytics = firebaseAnalytics;
    }

    @Override public void LogEventScreen(String eventName) {
        LogEvent(CATEGORY_SCREEN, eventName);
    }

    @Override public void LogEventClick(String eventName) {
        LogEvent(CATEGORY_CLICK, eventName);
    }

    @Override public void LogEvent(String category, String eventName) {
        Bundle parameters = new Bundle();
        parameters.putString(com.google.firebase.analytics.FirebaseAnalytics.Param.CONTENT_TYPE,
                eventName);

        synchronized (firebaseAnalytics) {
            firebaseAnalytics.logEvent(category, parameters);
        }
    }

    @Override public void LogException(String exception) {
        FirebaseCrash.log(exception);
    }

    @Override public void setUserProperty(String experimentName,
            IRemoteConfig.ExperimentVariant experimentVariant) {
        synchronized (firebaseAnalytics) {
            firebaseAnalytics.setUserProperty(experimentName, experimentVariant.name());
        }
    }
}
