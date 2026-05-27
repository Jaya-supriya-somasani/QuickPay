package com.example.quickpay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.quickpay.core.AppNavigation
import com.example.quickpay.ui.theme.QuickPayTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        var keepSplashOnScreen = true

        splashScreen.setKeepOnScreenCondition {
            keepSplashOnScreen
        }

        lifecycleScope.launch {
            delay(500)
            keepSplashOnScreen = false
        }
        enableEdgeToEdge()
        setContent {
            QuickPayTheme {
                AppNavigation()
            }
        }
    }
}