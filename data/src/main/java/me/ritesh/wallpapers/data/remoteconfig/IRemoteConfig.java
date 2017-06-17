package me.ritesh.wallpapers.data.remoteconfig;

public interface IRemoteConfig {
    static final String EXPERIMENT_ABOUT_MENU = "on_boarding_about";

    ExperimentVariant getExperimentVariant(String key);

    enum ExperimentVariant {
        NONE, VARIANT_A, VARIANT_B
    }
}
