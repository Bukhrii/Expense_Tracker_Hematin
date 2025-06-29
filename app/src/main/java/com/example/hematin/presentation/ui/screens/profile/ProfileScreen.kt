package com.example.hematin.presentation.ui.screens.profile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.EnhancedEncryption
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hematin.R
import com.example.hematin.presentation.ui.components.Icons.BackButtonIconOnprimary
import com.example.hematin.presentation.ui.components.Icons.NotificationButtonIcon
import com.example.hematin.presentation.ui.components.bottomNav.BottomNavigation
import com.example.hematin.presentation.ui.screens.auth.AuthState
import com.example.hematin.presentation.ui.screens.auth.AuthViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel(),
    onLogout: () -> Unit
) {

    val profileState by profileViewModel.state
    val authState by authViewModel.authState.observeAsState()
    val context = LocalContext.current

    val user = profileState.user

    var username by remember(profileState.user?.username) { mutableStateOf(profileState.user?.username ?: "") }
    var phoneNumber by remember(profileState.user?.phoneNumber) { mutableStateOf(profileState.user?.phoneNumber ?: "") }

    LaunchedEffect(profileState.updateSuccess) {
        if (profileState.updateSuccess) {
            Toast.makeText(context, "Profil berhasil diperbarui!", Toast.LENGTH_SHORT).show()
            profileViewModel.onUpdateSuccessShown()
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            bottomBar = {
                Box(contentAlignment = Alignment.Center,modifier = Modifier.fillMaxWidth()) {
                    BottomNavigation(navController = navController)
                }
            }
        ) { paddingValues ->
            Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingValues),
                ) {
                    Image(painter = painterResource(R.drawable.ic_topbar), contentDescription = null, modifier = Modifier.fillMaxWidth())

                    Column( modifier = Modifier.padding(top = 60.dp)) {
                        Column(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 25.dp)
                        ) {

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                BackButtonIconOnprimary(onClick = { navController.popBackStack() })
                                Text(
                                    text = stringResource(R.string.profile),
                                    style = MaterialTheme.typography.titleLarge,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                )
                                NotificationButtonIcon()
                            }
                        }

                        Spacer(modifier = Modifier.padding(vertical = 8.dp))

                        Column(
                            modifier = Modifier
                                .fillMaxSize() // Agar bisa di-scroll
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            if (profileState.isLoading && profileState.user == null) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(48.dp),
                                    color = MaterialTheme.colorScheme.primary
                                )
                            } else if (profileState.user != null) {
                                // -- Tampilan Profil --
                                Image(
                                    painter = painterResource(R.drawable.user_profile), // Ganti dengan gambar profil dinamis jika ada
                                    contentDescription = "Profile Picture",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(230.dp)
                                        .padding(15.dp)
                                        .clip(CircleShape)
                                )

                                Text(
                                    text = user?.username ?: "Pengguna",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = MaterialTheme.colorScheme.onSurface,
                                )

                                Text(
                                    text = user?.email ?: "Email tidak tersedia",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.secondary,
                                    fontWeight = Bold,
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                // -- READ Data: Menampilkan data pengguna dari ViewModel --
                                OutlinedTextField(
                                    value = username,
                                    onValueChange = { username = it },
                                    label = { Text("Username") },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                    modifier = Modifier.fillMaxWidth()
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                OutlinedTextField(
                                    value = phoneNumber,
                                    onValueChange = { phoneNumber = it },
                                    label = { Text("Nomor Telepon") },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                                    modifier = Modifier.fillMaxWidth()
                                )

                                Spacer(modifier = Modifier.height(24.dp))

                                // -- UPDATE Data: Tombol untuk menyimpan perubahan --
                                Button(
                                    onClick = { profileViewModel.updateUserProfile(username, phoneNumber) },
                                    enabled = !profileState.isLoading,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    if (profileState.isLoading) {
                                        CircularProgressIndicator(modifier = Modifier.size(24.dp))
                                    } else {
                                        Text("Simpan Perubahan")
                                    }
                                }
                                Spacer(modifier = Modifier.height(8.dp))

                                // -- LOGOUT: Tombol untuk keluar --
                                Button(
                                    onClick = {
                                        authViewModel.signOut()
                                        onLogout() }
                                      ,
                                    enabled = authState !is AuthState.Loading,
                                    colors = ButtonDefaults.buttonColors(),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    if (authState is AuthState.Loading) {
                                        CircularProgressIndicator(
                                            color = MaterialTheme.colorScheme.onPrimary,
                                            modifier = Modifier.size(24.dp),
                                            strokeWidth = 2.dp
                                        )
                                    } else {
                                        Text(text = stringResource(R.string.log_out))
                                    }
                                }

                            } else if (profileState.error != null) {
                                Text("Gagal memuat profil: ${profileState.error}", color = MaterialTheme.colorScheme.error)
                            }

                            Spacer(modifier = Modifier.height(20.dp))
                        }
                    }
                }
            }
        }
    }
}