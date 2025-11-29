package com.liquiditylens.utils

import android.content.Context
import android.graphics.Bitmap
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.drawToBitmap

/**
 * Helper to capture Composable content as Bitmap.
 */
object ComposableCapture {
    
    /**
     * Capture a View as Bitmap.
     * This is used to screenshot the entire OrderBook canvas.
     */
    fun captureView(view: View): Bitmap? {
        return try {
            view.drawToBitmap()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    
    /**
     * Find the parent view of the current Compose hierarchy.
     * This allows us to screenshot the entire screen.
     */
    fun findComposeView(context: Context): View? {
        return if (context is android.app.Activity) {
            context.window.decorView.findViewById(android.R.id.content)
        } else {
            null
        }
    }
}
