package com.example.hematin.presentation.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.EnhancedEncryption
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hematin.R
import com.example.hematin.presentation.ui.components.Icons.BackButtonIconOnprimary
import com.example.hematin.presentation.ui.components.Icons.NotificationButtonIcon
import com.example.hematin.presentation.ui.components.bottomNav.BottomNavigation

@Composable
fun ProfileScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            bottomBar = {
                Box(contentAlignment = Alignment.Center,modifier = Modifier.fillMaxWidth()) {
                    BottomNavigation(navController = navController)
                }
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
                ) {
                Image(painter = painterResource(R.drawable.ic_topbar), contentDescription = null, modifier = Modifier.fillMaxWidth())

                Column(
                    modifier = Modifier.padding(
                        top = 60.dp,
                    )
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 25.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            BackButtonIconOnprimary()
                            Text(
                                text = stringResource(R.string.profile),
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = Bold,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                            NotificationButtonIcon()
                        }
                        Spacer(modifier = Modifier.padding(vertical = 8.dp))

                        Column(horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Image(
                                painter = painterResource(R.drawable.user_profile),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(250.dp)
                                    .padding(15.dp)
                                    .clip(CircleShape)
                            )
                            Text(
                                text = stringResource(R.string.full_name),
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.DarkGray,
                                fontWeight = Bold,
                                fontSize = 20.sp
                            )
                            Text(
                                text = stringResource(R.string.user_name),
                                style = MaterialTheme.typography.bodyMedium,
                                color = colorResource(R.color.secondary_light_green),
                                fontSize = 16.sp
                            )
                        }

                        Spacer(modifier = Modifier.padding(vertical = 8.dp))

                        Row {
                            Icon(
                                imageVector = Icons.Filled.Diamond,
                                contentDescription = null,
                                tint = Color.DarkGray,
                                modifier = Modifier.size(30.dp)
                            )
                            Spacer(modifier = Modifier.padding(8.dp))
                            Text(
                                text = stringResource(R.string.invite_friends),
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.DarkGray,
                                fontSize = 20.sp
                            )
                        }
                        Spacer(modifier = Modifier.padding(vertical = 8.dp))
                        Row {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = null,
                                tint = Color.DarkGray,
                                modifier = Modifier.size(30.dp)
                            )
                            Spacer(modifier = Modifier.padding(8.dp))
                            Text(
                                text = stringResource(R.string.account_info),
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.DarkGray,
                                fontSize = 20.sp
                            )
                        }
                        Spacer(modifier = Modifier.padding(vertical = 8.dp))
                        Row {
                            Icon(
                                imageVector = Icons.Filled.People,
                                contentDescription = null,
                                tint = Color.DarkGray,
                                modifier = Modifier.size(30.dp)
                            )
                            Spacer(modifier = Modifier.padding(8.dp))
                            Text(
                                text = stringResource(R.string.personal_profile),
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.DarkGray,
                                fontSize = 20.sp
                            )
                        }
                        Spacer(modifier = Modifier.padding(vertical = 8.dp))
                        Row {
                            Icon(
                                imageVector = Icons.Filled.Mail,
                                contentDescription = null,
                                tint = Color.DarkGray,
                                modifier = Modifier.size(30.dp)
                            )
                            Spacer(modifier = Modifier.padding(8.dp))
                            Text(
                                text = stringResource(R.string.message_center),
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.DarkGray,
                                fontSize = 20.sp
                            )
                        }
                        Spacer(modifier = Modifier.padding(vertical = 8.dp))
                        Row {
                            Icon(
                                imageVector = Icons.Filled.Security,
                                contentDescription = null,
                                tint = Color.DarkGray,
                                modifier = Modifier.size(30.dp)
                            )
                            Spacer(modifier = Modifier.padding(8.dp))
                            Text(
                                text = stringResource(R.string.login_and_security),
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.DarkGray,
                                fontSize = 20.sp
                            )
                        }
                        Spacer(modifier = Modifier.padding(vertical = 8.dp))
                        Row {
                            Icon(
                                imageVector = Icons.Filled.EnhancedEncryption,
                                contentDescription = null,
                                tint = Color.DarkGray,
                                modifier = Modifier.size(30.dp)
                            )
                            Spacer(modifier = Modifier.padding(8.dp))
                            Text(
                                text = stringResource(R.string.data_and_privacy),
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.DarkGray,
                                fontSize = 20.sp
                            )
                        }
                    }
                }
            }
        }
    }
}