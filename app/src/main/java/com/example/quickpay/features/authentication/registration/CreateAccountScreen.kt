package com.example.quickpay.features.authentication.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quickpay.core.AppColors
import com.example.quickpay.core.AppSizes
import com.example.quickpay.core.FontSizes

@Composable
fun CreateAccountScreen(
    onContinue: (String, String) -> Unit,
    onLogin: () -> Unit,
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
                        value = viewModel.email,
                        onValueChange = { viewModel.onEmailChange(it) },
                        label = { Text("Email") },
                        isError = viewModel.emailError != null,
                        supportingText = viewModel.emailError?.let { { Text(it) } },
                        modifier = Modifier.fillMaxWidth(),
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
                    )

                    Spacer(modifier = Modifier.height(AppSizes.size32))

                    // Continue Button
                    Button(
                        onClick = {
                            if (viewModel.validate()) {
                                onContinue(viewModel.email, viewModel.phoneNumber)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(AppSizes.size56),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppColors.primaryColor,
                            contentColor = AppColors.whiteFeather
                        ),
                        shape = RoundedCornerShape(AppSizes.size12)
                    ) {
                        Text("Continue", fontSize = FontSizes.size18, fontWeight = FontWeight.Bold)
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = AppSizes.size24)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text("or", fontSize = FontSizes.size18)
                    }

                    TextButton(
                        onClick = onLogin,
                        modifier = Modifier
                            .padding(top = AppSizes.size8)
                            .fillMaxWidth(),
                    ) {
                        Text(
                            "Login",
                            fontSize = FontSizes.size18,
                            fontWeight = FontWeight.Bold,
                            color = AppColors.primaryColor
                        )
                    }
                }
            }
        }
    }
}