package com.example.quickpay.features.authentication.registration

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quickpay.core.AppColors
import com.example.quickpay.core.AppSizes

private fun isValidEmail(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

private fun isValidPassword(password: String): Boolean {
    val hasUppercase = password.any { it.isUpperCase() }
    val hasLowercase = password.any { it.isLowerCase() }
    val hasDigit = password.any { it.isDigit() }
    val hasSpecialChar = password.any { !it.isLetterOrDigit() }
    return hasUppercase && hasLowercase && hasDigit && hasSpecialChar
}

@Composable
fun CreateAccountScreen(
    onContinue: (String, String) -> Unit,
    viewModel: CreateAccountViewModel = viewModel()
) {
    Box(modifier = Modifier.background(color = AppColors.primaryColor)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(top = AppSizes.size100))
            Text(
                "Create Account",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = AppColors.whiteFeather
            )
            Box(
                modifier = Modifier
                    .padding(top = AppSizes.size60)
                    .clip(RoundedCornerShape(topStart = AppSizes.size24, topEnd = AppSizes.size24))
                    .fillMaxSize()
                    .background(color = AppColors.whiteFeather)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = AppSizes.size16)
                ) {
                    Spacer(modifier = Modifier.height(AppSizes.size26))

                    // Email
                    OutlinedTextField(
                        value = viewModel.email,                        // ✅ from ViewModel
                        onValueChange = { viewModel.onEmailChange(it) }, // ✅ update ViewModel
                        label = { Text("Email") },
                        isError = viewModel.emailError != null,
                        supportingText = viewModel.emailError?.let { { Text(it) } },
                        modifier = Modifier.fillMaxWidth(),
                        // ... rest of your colors
                    )

                    Spacer(modifier = Modifier.height(AppSizes.size16))

                    // Phone
                    OutlinedTextField(
                        value = viewModel.phoneNumber,
                        onValueChange = { viewModel.onPhoneNumberChange(it) },
                        label = { Text("Phone Number") },
                        isError = viewModel.phoneNumberError != null,
                        supportingText = viewModel.phoneNumberError?.let { { Text(it) } },
                        modifier = Modifier.fillMaxWidth(),
                        // ... rest of your colors
                    )

                    Spacer(modifier = Modifier.height(AppSizes.size16))

                    // Password
                    OutlinedTextField(
                        value = viewModel.password,
                        onValueChange = { viewModel.onPasswordChange(it) },
                        label = { Text("Password") },
                        isError = viewModel.passwordError != null,
                        supportingText = viewModel.passwordError?.let { { Text(it) } },
                        visualTransformation = if (viewModel.passwordVisible)
                            VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = { viewModel.togglePasswordVisible() }) {
                                Icon(
                                    imageVector = if (viewModel.passwordVisible)
                                        Icons.Default.Favorite else Icons.Default.Person,
                                    contentDescription = null,
                                    tint = AppColors.whiteFeather
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        // ... rest of your colors
                    )

                    Spacer(modifier = Modifier.height(AppSizes.size16))

                    // Confirm Password
                    OutlinedTextField(
                        value = viewModel.confirmPassword,
                        onValueChange = { viewModel.onConfirmPasswordChange(it) },
                        label = { Text("Confirm Password") },
                        isError = viewModel.confirmPasswordError != null,
                        supportingText = viewModel.confirmPasswordError?.let { { Text(it) } },
                        visualTransformation = if (viewModel.confirmPasswordVisible)
                            VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = { viewModel.toggleConfirmPasswordVisible() }) {
                                Icon(
                                    imageVector = if (viewModel.confirmPasswordVisible)
                                        Icons.Default.Favorite else Icons.Default.Person,
                                    contentDescription = null,
                                    tint = AppColors.whiteFeather
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        // ... rest of your colors
                    )

                    Spacer(modifier = Modifier.height(AppSizes.size32))

                    // Continue Button
                    Button(
                        onClick = {
                            if (viewModel.validate()) {          // ✅ validate in ViewModel
                                onContinue(viewModel.email, viewModel.phoneNumber)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppColors.primaryColor,
                            contentColor = AppColors.whiteFeather
                        ),
                        shape = RoundedCornerShape(AppSizes.size12)
                    ) {
                        Text("Continue", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}