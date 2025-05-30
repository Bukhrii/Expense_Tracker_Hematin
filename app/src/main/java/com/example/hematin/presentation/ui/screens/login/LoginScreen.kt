package com.example.hematin.presentation.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavController
import com.example.hematin.presentation.ui.navigation.Screen

@Composable
fun LoginScreen(navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color.White)
    ) {
        TextButton(
            onClick = {
                navController.navigate(Screen.homeScreen)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(
                text = stringResource(R.string.skip),
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(horizontal = 30.dp)
        ) {

            Image(
                painter = painterResource(R.drawable.login),
                contentDescription = stringResource(R.string.sign_in),
                modifier = Modifier.size(250.dp)

            )

            Text(
                text = stringResource(id = R.string.sign_in),
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.padding(10.dp))

            var textEmail by remember { mutableStateOf("") }
            OutlinedTextField(
                value = textEmail,
                onValueChange = { textEmail = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = stringResource(id = R.string.email),
                        modifier = Modifier.size(30.dp)
                    )
                },
                label = { Text(stringResource(R.string.email)) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(R.color.primary_green),
                    focusedLabelColor = colorResource(R.color.primary_green)
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(4.dp))

            var textPassword by remember { mutableStateOf("") }
            var passwordVisible by remember { mutableStateOf(false) }
            OutlinedTextField(
                value = textPassword,
                onValueChange = { textPassword = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = stringResource(id = R.string.email),
                        modifier = Modifier.size(30.dp)
                    )
                },
                label = { Text(stringResource(R.string.password)) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(R.color.primary_green),
                    focusedLabelColor = colorResource(R.color.primary_green)
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image =
                        if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff

                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, description)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            TextButton(
                onClick = {
                    navController.navigate(Screen.resetPasswordScreen)
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    text = stringResource(R.string.forgot_password),
                    fontSize = 12.sp,
                    color = Color.Gray,
                )
            }

            Button(
                onClick = {
                    navController.navigate(Screen.homeScreen)
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.primary_green)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(text = stringResource(R.string.sign_in))
            }

            Spacer(modifier = Modifier.padding(14.dp))

            Text(
                text = stringResource(R.string.continue_text),
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.padding(4.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Button(onClick = {
                    navController.navigate(Screen.homeScreen)
                }, shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.primary_green)
                    ),
                    modifier = Modifier
                        .weight(0.3f)
                        .height(48.dp))
                {
                    Icon(
                        painter = painterResource(R.drawable.google_icon),
                        contentDescription = stringResource(id = R.string.continue_google),
                        tint = Color.Unspecified,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(text = stringResource(R.string.continue_google))
                }

                Spacer(modifier = Modifier.padding(8.dp))

                Button(onClick = {
                    navController.navigate(Screen.homeScreen)
                },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.primary_green)
                    ),
                    modifier = Modifier
                        .weight(0.3f)
                        .height(48.dp))
                {
                    Icon(
                        painter = painterResource(R.drawable.facebook_icon),
                        contentDescription = stringResource(id = R.string.continue_facebook),
                        tint = Color.Unspecified,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(text = stringResource(R.string.continue_facebook))
                }
            }

            Spacer(modifier = Modifier.padding(4.dp))

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
        }

    }
}