package com.project.suitmediaaryabagusf.utils

import android.content.Context
import android.widget.Toast

object Utils {
    fun shortToast(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}