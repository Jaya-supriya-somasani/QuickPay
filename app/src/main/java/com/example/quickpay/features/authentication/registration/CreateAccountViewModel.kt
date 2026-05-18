package com.example.quickpay.features.authentication.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CreateAccountViewModel : ViewModel() {

    var email by mutableStateOf("")
        private set

    var phoneNumber by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var confirmPassword by mutableStateOf("")
        private set

    // Error states
    var emailError by mutableStateOf<String?>(null)
        private set

    var phoneNumberError by mutableStateOf<String?>(null)
        private set

    var passwordError by mutableStateOf<String?>(null)
        private set

    var confirmPasswordError by mutableStateOf<String?>(null)
        private set

    // UI states
    var passwordVisible by mutableStateOf(false)
        private set

    var confirmPasswordVisible by mutableStateOf(false)
        private set

    // Update functions
    fun onEmailChange(value: String) {
        email = value
        emailError = null
    }

    fun onPhoneNumberChange(value: String) {
        if (value.length <= 10) {
            phoneNumber = value.filter { it.isDigit() }
            phoneNumberError = null
        }
    }

    fun onPasswordChange(value: String) {
        password = value
        passwordError = null
    }

    fun onConfirmPasswordChange(value: String) {
        confirmPassword = value
        confirmPasswordError = null
    }

    fun togglePasswordVisible() {
        passwordVisible = !passwordVisible
    }

    fun toggleConfirmPasswordVisible() {
        confirmPasswordVisible = !confirmPasswordVisible
    }

    // Validate and return true if no errors
    fun validate(): Boolean {
        var hasError = false

        if (email.isEmpty()) {
            emailError = "Email is required"
            hasError = true
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailError = "Invalid email format"
            hasError = true
        }

        if (phoneNumber.isEmpty()) {
            phoneNumberError = "Phone number is required"
            hasError = true
        } else if (phoneNumber.length != 10) {
            phoneNumberError = "Phone number must be 10 digits"
            hasError = true
        }

        if (password.isEmpty()) {
            passwordError = "Password is required"
            hasError = true
        } else if (password.length < 8) {
            passwordError = "Password must be at least 8 characters"
            hasError = true
        } else if (!isValidPassword(password)) {
            passwordError =
                "Password must contain uppercase, lowercase, number and special character"
            hasError = true
        }

        if (confirmPassword.isEmpty()) {
            confirmPasswordError = "Please confirm your password"
            hasError = true
        } else if (password != confirmPassword) {
            confirmPasswordError = "Passwords do not match"
            hasError = true
        }

        return !hasError
    }

    private fun isValidPassword(password: String): Boolean {
        return password.any { it.isUpperCase() } &&
                password.any { it.isLowerCase() } &&
                password.any { it.isDigit() } &&
                password.any { !it.isLetterOrDigit() }
    }
}