package me.ritesh.wallpapers.dependency;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.ritesh.wallpapers.MainApplication;
import me.ritesh.wallpapers.imageloader.GlideLoader;
import me.ritesh.wallpapers.imageloader.IImageLoader;

/**
 * @author Ritesh Shakya
 */
@Module public class ImageModule {
    @Singleton @Provides IImageLoader provideImageLoader(MainApplication mainApplication) {
        return new GlideLoader(mainApplication);
    }
}
