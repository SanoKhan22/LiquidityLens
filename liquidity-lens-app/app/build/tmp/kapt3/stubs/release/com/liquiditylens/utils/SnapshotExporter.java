package com.liquiditylens.utils;

/**
 * Utility for capturing and sharing Canvas snapshots.
 * This is the viral growth engine - every shared image = free marketing.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004J\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0004J \u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\r\u00a8\u0006\u000e"}, d2 = {"Lcom/liquiditylens/utils/SnapshotExporter;", "", "()V", "addWatermark", "Landroid/graphics/Bitmap;", "bitmap", "saveToCacheAndGetUri", "Landroid/net/Uri;", "context", "Landroid/content/Context;", "shareSnapshot", "", "text", "", "app_release"})
public final class SnapshotExporter {
    @org.jetbrains.annotations.NotNull()
    public static final com.liquiditylens.utils.SnapshotExporter INSTANCE = null;
    
    private SnapshotExporter() {
        super();
    }
    
    /**
     * Add watermark to bitmap with LiquidityLens branding.
     */
    @org.jetbrains.annotations.NotNull()
    public final android.graphics.Bitmap addWatermark(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap) {
        return null;
    }
    
    /**
     * Save bitmap to cache and return shareable URI.
     */
    @org.jetbrains.annotations.Nullable()
    public final android.net.Uri saveToCacheAndGetUri(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap) {
        return null;
    }
    
    /**
     * Share snapshot via Android share sheet.
     */
    public final void shareSnapshot(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap, @org.jetbrains.annotations.NotNull()
    java.lang.String text) {
    }
}