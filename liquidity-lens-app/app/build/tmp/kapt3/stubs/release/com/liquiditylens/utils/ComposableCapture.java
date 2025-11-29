package com.liquiditylens.utils;

/**
 * Helper to capture Composable content as Bitmap.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\t\u00a8\u0006\n"}, d2 = {"Lcom/liquiditylens/utils/ComposableCapture;", "", "()V", "captureView", "Landroid/graphics/Bitmap;", "view", "Landroid/view/View;", "findComposeView", "context", "Landroid/content/Context;", "app_release"})
public final class ComposableCapture {
    @org.jetbrains.annotations.NotNull()
    public static final com.liquiditylens.utils.ComposableCapture INSTANCE = null;
    
    private ComposableCapture() {
        super();
    }
    
    /**
     * Capture a View as Bitmap.
     * This is used to screenshot the entire OrderBook canvas.
     */
    @org.jetbrains.annotations.Nullable()
    public final android.graphics.Bitmap captureView(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
        return null;
    }
    
    /**
     * Find the parent view of the current Compose hierarchy.
     * This allows us to screenshot the entire screen.
     */
    @org.jetbrains.annotations.Nullable()
    public final android.view.View findComposeView(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
}