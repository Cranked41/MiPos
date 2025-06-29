package com.cranked.mipos.extensions

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun Context.showToast(scope: CoroutineScope, message: String) {
    scope.launch(Dispatchers.Main) {
        Toast.makeText(this@showToast, message, Toast.LENGTH_SHORT).show()
    }
}