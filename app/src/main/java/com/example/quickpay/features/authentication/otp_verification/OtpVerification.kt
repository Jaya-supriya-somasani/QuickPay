package com.example.quickpay.features.authentication.otp_verification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quickpay.core.AppColors
import com.example.quickpay.core.AppSizes
import com.example.quickpay.core.FontSizes

@Composable
fun OtpScreen(
    phoneNumber: String,
    onVerified: () -> Unit,
    onBack: () -> Unit
) {
    var otp by remember { mutableStateOf("") }
    var otpError by remember { mutableStateOf<String?>(null) }

    Box(modifier = Modifier.background(AppColors.primaryColor)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = AppSizes.size100, start = AppSizes.size16),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = AppColors.whiteFeather
                    )
                }
                Text(
                    "OTP Verification",
                    color = AppColors.whiteFeather,
                    fontSize = FontSizes.size20,
                    fontWeight = FontWeight.SemiBold
                )
            }

            // White card
            Box(
                modifier = Modifier
                    .padding(top = AppSizes.size60)
                    .clip(RoundedCornerShape(topStart = AppSizes.size24, topEnd = AppSizes.size24))
                    .fillMaxSize()
                    .background(AppColors.whiteFeather)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = AppSizes.size16),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(AppSizes.size32))

                    Text(
                        text = "Enter the 6-digit OTP sent to",
                        color = AppColors.primaryColor,
                        fontSize = FontSizes.size16
                    )
                    Text(
                        text = phoneNumber,
                        color = AppColors.primaryColor,
                        fontSize = FontSizes.size16,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(AppSizes.size32))

                    // OTP Input
                    OutlinedTextField(
                        value = otp,
                        onValueChange = {
                            if (it.length <= 6) {
                                otp = it.filter { c -> c.isDigit() }
                                otpError = null
                            }
                        },
                        label = { Text("Enter OTP") },
                        placeholder = { Text("6-digit OTP") },
                        isError = otpError != null,
                        supportingText = otpError?.let { { Text(it) } },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                        textStyle = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Center,
                            letterSpacing = FontSizes.size8,
                            fontSize = FontSizes.size20
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(AppSizes.size16))

                    // Resend OTP
                    TextButton(onClick = { /* call resend OTP API */ }) {
                        Text("Resend OTP", color = AppColors.primaryColor)
                    }

                    Spacer(modifier = Modifier.height(AppSizes.size32))

                    // Verify Button
                    Button(
                        onClick = {
                            when {
                                otp.isEmpty() -> otpError = "OTP is required"
                                otp.length != 6 -> otpError = "Enter valid 6-digit OTP"
                                else -> onVerified()
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
                        Text(
                            "Verify OTP",
                            fontSize = FontSizes.size18,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}