package com.example.quickpay.core

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quickpay.features.authentication.login.LoginScreen
import com.example.quickpay.features.authentication.otp_verification.OtpScreen
import com.example.quickpay.features.authentication.registration.CreateAccountScreen
import com.example.quickpay.features.authentication.registration.CreateAccountViewModel
import com.example.quickpay.features.home.HomeScreen
import com.example.quickpay.features.splash.SplashScreen

object Routes {
    const val SPLASH = "splash"
    const val CREATE_ACCOUNT = "createAccount"
    const val HOME = "home"
    const val DETAIL = "detail"
    const val PROFILE = "profile"
    const val LOGIN = "login"
    const val OTP = "otp/{phoneNumber}"
    fun otpRoute(phoneNumber: String) = "otp/$phoneNumber"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {
        // Splash Screen
        composable(Routes.SPLASH) {
            SplashScreen {
                navController.navigate(Routes.CREATE_ACCOUNT) {
                    popUpTo(Routes.SPLASH) { inclusive = true }
                }
            }
        }

        composable(Routes.CREATE_ACCOUNT) { backStackEntry ->
            val viewModel: CreateAccountViewModel =
                viewModel(backStackEntry)

            CreateAccountScreen(
                viewModel = viewModel,
                onContinue = { email, phoneNumber ->
                    navController.navigate(Routes.otpRoute(phoneNumber))
                },
                onLogin = {
                    navController.navigate(Routes.LOGIN)
                }
            )
        }
        composable(
            route = Routes.OTP,
            arguments = listOf(navArgument("phoneNumber") { type = NavType.StringType })
        ) { backStackEntry ->
            val phoneNumber = backStackEntry.arguments?.getString("phoneNumber") ?: ""
            OtpScreen(
                phoneNumber = phoneNumber,
                onVerified = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                    }
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable (Routes.LOGIN){
            LoginScreen()
        }

        // Home Screen
        composable(Routes.HOME) {
            HomeScreen(navController)
        }
    }
}