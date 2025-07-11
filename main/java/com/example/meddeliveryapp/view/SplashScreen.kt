package com.example.meddeliveryapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.meddeliveryapp.R
import kotlinx.coroutines.delay

@Composable
fun Splash(navController: NavController) {

    LaunchedEffect(Unit) {
        delay(3000) // 3 seconds
        navController.navigate("login") {
            popUpTo("splash") { inclusive = true }
        }
    }


    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFF61A72E)),
        contentAlignment = Alignment.Center){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.carbon_delivery),
                contentDescription = "icon",
                contentScale = ContentScale.None
            )
            Text(
                text = "MediSwift",
                style = MaterialTheme.typography.headlineLarge,
                color = Color(0xFFFFFFFF)
            )
            Text(
                text = "Deliver Medicine With Ease",
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFFFFFFFF)
            )
        }
    }
}