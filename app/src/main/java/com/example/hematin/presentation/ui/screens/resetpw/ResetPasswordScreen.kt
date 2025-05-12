package com.example.hematin.presentation.ui.screens.resetpw

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.hematin.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import com.example.hematin.presentation.ui.navigation.Screen

@Composable
fun ResetPasswordScreen(navController: NavController) {

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 30.dp)
        ) {

            Image(
                painter = painterResource(R.drawable.forgot_password),
                contentDescription = stringResource(R.string.change_password),
                modifier = Modifier.size(250.dp)

            )

            Text(
                text = stringResource(id = R.string.change_password),
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.padding(10.dp))

            var textRegisteredEmail by remember { mutableStateOf("") }
            OutlinedTextField(
                value = textRegisteredEmail,
                onValueChange = { textRegisteredEmail = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = stringResource(id = R.string.registered_email),
                        modifier = Modifier.size(30.dp)
                    )
                },
                label = { Text(stringResource(R.string.registered_email)) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(R.color.primary_green),
                    focusedLabelColor = colorResource(R.color.primary_green)
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(12.dp))

            Button(
                onClick = {
                    navController.navigate(Screen.loginScreen)
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.primary_green)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(text = stringResource(R.string.change_password))
            }

            Spacer(modifier = Modifier.padding(14.dp))

            Row(horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()) {
                Text(
                    stringResource(R.string.dont_have_account_text),
                    fontSize = 14.sp
                )
                TextButton(
                    onClick = {
                        navController.navigate(Screen.signupScreen)
                    }
                ) {
                    Text(
                        text = stringResource(R.string.create_now_text),
                        color = colorResource(R.color.primary_dark_green),
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.padding(8.dp))

            Row(horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()) {
                Text(
                    stringResource(R.string.already_have_account),
                    fontSize = 14.sp
                )
                TextButton(
                    onClick = {
                        navController.navigate(Screen.loginScreen)
                    }
                ) {
                    Text(
                        text = stringResource(R.string.sign_in),
                        color = colorResource(R.color.primary_dark_green),
                        fontSize = 14.sp
                    )
                }
            }
        }

    }
}