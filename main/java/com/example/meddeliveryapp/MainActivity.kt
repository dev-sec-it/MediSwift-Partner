package com.example.meddeliveryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.meddeliveryapp.ui.theme.MedDeliveryAppTheme
import com.example.meddeliveryapp.view.HelpScreen
import com.example.meddeliveryapp.view.HomeScreen
import com.example.meddeliveryapp.view.Login
import com.example.meddeliveryapp.view.OrderDetails
import com.example.meddeliveryapp.view.SignUp
import com.example.meddeliveryapp.view.Splash
import com.example.meddeliveryapp.view.TrackingScreen
import com.example.meddeliveryapp.view.pages.OrderScreen
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()
        setContent {
            MedDeliveryAppTheme {
                AuthApp()
            }
        }
    }
}

@Composable
fun AuthApp(){ //Navigation among all screens
    val navController = rememberNavController()
    NavHost(navController, startDestination = "splash") {
        composable("splash") { Splash(navController) }
        composable("login") { Login(navController) }
        composable("signup") { SignUp(navController) }
        composable("home") { HomeScreen(navController= navController) }
        composable("orderDetails") { OrderDetails(navController) }
        composable("orders") { OrderScreen(navController=navController) }
        composable("track") { TrackingScreen(navController) }
        composable("help") { HelpScreen(navController) }
    }
}