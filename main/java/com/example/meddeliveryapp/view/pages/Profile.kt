package com.example.meddeliveryapp.view.pages

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.meddeliveryapp.R
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(
    modifier: Modifier = Modifier,
    navController: NavController,
    onDeleteAccount: () -> Unit
) {
    val backgroundColor = Color(0xFF61A72E)
    val lightGreen = Color(0xFFB2E59E)
    val cardColor = Color.White
    val textColor = Color.Black

    var shouldLogout by remember { mutableStateOf(false) }

    //Navigate after logout safely
    LaunchedEffect(shouldLogout) {
        if (shouldLogout) {
            navController.navigate("login") {
                popUpTo("home") { inclusive = true }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Settings", color = Color.White, fontWeight = FontWeight.Bold)
                },
                navigationIcon = {
                    IconButton(onClick = { /* Add back navigation if needed */ }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = backgroundColor
                )
            )
        },
        containerColor = backgroundColor
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // Profile Section
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.dp),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("John Doe", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.White)
                Text("John.Doe.65@gmail.com", color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                        .clickable(
                            onClick = { }
                        )
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Settings Options
            val settingsItems = listOf(
                Triple("Account Settings", Icons.Default.Person) { /* Handle Account Settings */ },
                //Triple("Notifications", Icons.Default.Notifications) { /* Handle Notifications */ },
                Triple("Saved Addresses", Icons.Default.Place) { /* Handle Addresses */ },
                Triple("Privacy", Icons.Default.Lock) { /* Handle Privacy */ },
                Triple("Help and Support", Icons.Default.Info) { /* Handle Help */ }
            )

            settingsItems.forEach { (title, icon, onClick) ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable { onClick() },
                    colors = CardDefaults.cardColors(containerColor = cardColor),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(lightGreen),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(imageVector = icon, contentDescription = title, tint = backgroundColor)
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = title, color = textColor, fontSize = 16.sp, modifier = Modifier.weight(1f))
                        Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null, tint = Color.Gray)
                    }
                }
            }

            // Log Out
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable {
                        Firebase.auth.signOut()
                        shouldLogout = true
                    },
                colors = CardDefaults.cardColors(containerColor = cardColor),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(lightGreen),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "Log Out", tint = backgroundColor)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "Log Out", color = Color.Red, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                }
            }

            // Delete Account
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable { onDeleteAccount() },
                colors = CardDefaults.cardColors(containerColor = cardColor),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(lightGreen),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Account",
                            tint = backgroundColor
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "Delete Account", color = Color.Red, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}
