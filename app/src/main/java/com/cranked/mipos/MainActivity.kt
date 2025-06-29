package com.cranked.mipos.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.material3.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cranked.mipos.viewmodel.RestaurantViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val vm: RestaurantViewModel = viewModel()

            MaterialTheme {
                RestaurantScreen(viewModel = vm) // ✅ Artık burada çağrılıyor
            }
        }
    }
}