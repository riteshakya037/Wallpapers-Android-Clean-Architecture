package me.ritesh.wallpapers.data.analytics;

import me.ritesh.wallpapers.data.remoteconfig.IRemoteConfig;

/**
 * @author Ritesh Shakya
 */

public interface IAnalytics {
    void LogEventScreen(String eventName);

    void LogEventClick(String eventName);

    void LogEvent(String category, String eventName);

    void LogException(String exception);

    void setUserProperty(String experimentName, IRemoteConfig.ExperimentVariant experimentVariant);
}
