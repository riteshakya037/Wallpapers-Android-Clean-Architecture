package me.ritesh.wallpapers.data.remoteconfig;

import android.util.Log;
import com.google.android.gms.tasks.Continuation;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import me.ritesh.wallpapers.data.R;
import me.ritesh.wallpapers.data.analytics.IAnalytics;

/**
 * @author Ritesh Shakya
 */

public class RemoteConfig implements IRemoteConfig {
    private static final String TAG = "RemoteConfig";
    private FirebaseRemoteConfig firebaseRemoteConfig;

    @SuppressWarnings("ConstantConditions") public RemoteConfig(boolean isDebug, final IAnalytics analytics) {
        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings =
                new FirebaseRemoteConfigSettings.Builder().setDeveloperModeEnabled(isDebug).build();

        firebaseRemoteConfig.setConfigSettings(configSettings);
        firebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults);

        firebaseRemoteConfig.fetch().continueWith((Continuation<Void, Void>) task -> {
            if (task.isSuccessful()) {
                Log.e(TAG, "then: Success");
                firebaseRemoteConfig.activateFetched();
                analytics.setUserProperty(EXPERIMENT_ABOUT_MENU,
                        getExperimentVariant(EXPERIMENT_ABOUT_MENU));
                return null;
            }
            Log.e(TAG, "then: Failure");
            throw task.getException();
        });
    }

    @Override public ExperimentVariant getExperimentVariant(String key) {
        String result = firebaseRemoteConfig.getString(key);

        if (result != null && !result.isEmpty()) {
            return ExperimentVariant.valueOf(result.toUpperCase());
        }
        return ExperimentVariant.NONE;
    }
}
