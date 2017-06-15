package me.ritesh.wallpapers;

import android.app.Application;

import me.ritesh.wallpapers.dependency.ApplicationComponent;
import me.ritesh.wallpapers.dependency.DaggerApplicationComponent;
import me.ritesh.wallpapers.dependency.DataModule;
import me.ritesh.wallpapers.dependency.DomainModule;
import me.ritesh.wallpapers.dependency.ImageModule;
import me.ritesh.wallpapers.dependency.Rx;

/**
 * @author Ritesh Shakya
 */

public class MainApplication extends Application {
    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        inject();
    }

    private void inject() {
        component = DaggerApplicationComponent.builder()
                .dataModule(new DataModule(this))
                .domainModule(new DomainModule())
                .imageModule(new ImageModule())
                .rx(new Rx())
                .build();
        component.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return component;
    }
}
