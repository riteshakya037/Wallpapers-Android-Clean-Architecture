package me.ritesh.wallpapers.domain.remoteconfig;

public interface IRemoteConfig {
    String EXPERIMENT_ABOUT_MENU = "on_boarding_about";

    ExperimentVariant getExperimentVariant(String key);

    enum ExperimentVariant {
        NONE, VARIANT_A, VARIANT_B
    }
}
