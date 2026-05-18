package com.example.quickpay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quickpay.core.AppNavigation
import com.example.quickpay.features.authentication.registration.CreateAccountScreen
import com.example.quickpay.core.Routes
import com.example.quickpay.features.authentication.otp_verification.OtpScreen
import com.example.quickpay.features.authentication.registration.CreateAccountViewModel
import com.example.quickpay.features.home.HomeScreen
import com.example.quickpay.features.splash.SplashScreen
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