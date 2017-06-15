package me.ritesh.wallpapers.data.analytics;

/**
 * @author Ritesh Shakya
 */

public interface IAnalytics {
    void LogEventScreen(String eventName);

    void LogEventClick(String eventName);

    void LogEvent(String category, String eventName);

    void LogException(String exception);
}
