package me.ritesh.wallpapers.dependency;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.ritesh.wallpapers.MainApplication;
import me.ritesh.wallpapers.imageloader.IImageLoader;
import me.ritesh.wallpapers.imageloader.GlideLoader;

/**
 * @author Ritesh Shakya
 */
@Module
public class ImageModule {
    @Singleton
    @Provides
    IImageLoader provideImageLoader(MainApplication mainApplication) {
        return new GlideLoader(mainApplication);
    }
}
