package me.ritesh.wallpapers.config;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.GlideModule;

/**
 * @author Ritesh Shakya
 */

public class GlideConfiguration implements GlideModule {
    private static final int MAX_POOL = 10 * 1024 * 1024;

    @Override public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        builder.setBitmapPool(new LruBitmapPool(MAX_POOL));
        builder.setMemoryCache(new LruResourceCache(MAX_POOL));
        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565);
    }

    @Override public void registerComponents(Context context, Glide glide) {
    }
}