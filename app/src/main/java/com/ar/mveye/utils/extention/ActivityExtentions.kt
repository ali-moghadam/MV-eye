package com.ar.mveye.utils.extention

import android.app.Activity
import android.os.Build
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager


@Suppress("DEPRECATION")
fun Activity.hideStatusBar() {
    requestWindowFeature(Window.FEATURE_NO_TITLE)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window.insetsController?.hide(WindowInsets.Type.statusBars())
    } else {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
}