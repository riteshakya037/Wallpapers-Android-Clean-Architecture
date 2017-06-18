package me.ritesh.wallpapers.domain.analytics;

import me.ritesh.wallpapers.domain.remoteconfig.IRemoteConfig;

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
