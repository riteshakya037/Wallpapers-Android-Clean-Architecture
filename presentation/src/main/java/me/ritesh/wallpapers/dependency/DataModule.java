package me.ritesh.wallpapers.dependency;

import dagger.Module;
import dagger.Provides;
import me.ritesh.wallpapers.MainApplication;

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

}
