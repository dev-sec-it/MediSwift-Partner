package com.example.meddeliveryapp.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meddeliveryapp.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@Composable
fun SignUp(navController: NavController){
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    //val configuration = LocalConfiguration.current
    //val screenWidth = configuration.screenWidthDp.dp
    //val screenHeight = configuration.screenHeightDp.dp

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        contentAlignment = Alignment.Center){
        Column(horizontalAlignment = Alignment.CenterHorizontally){

            Image(
                painter = painterResource(id = R.drawable.mediswift_logo),
                contentDescription = "logo",
                contentScale = ContentScale.None
            )

            Spacer(modifier = Modifier.padding(28.dp))

            Text("Sign Up", style= MaterialTheme.typography.headlineLarge, color = Color(0xFF61A72E))
            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = {Text("Username")},
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = {Text("Email")},
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = {Text("Password")},
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = {Text("Confirm Password")},
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))

            val context= LocalContext.current

            Button(
                onClick = {
                    Firebase.auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(
                                    context,
                                    "Signup Successful! Please Log in.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                navController.navigate("login") {
                                    popUpTo("signup") { inclusive = true }
                                }
                            } else{
                                Toast.makeText(context, task.exception?.message?:"SignUp Failed",
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                        },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF61A72E),
                    contentColor = Color(0xFFFFFFFF)),
                modifier = Modifier.fillMaxWidth()){
                Text("REGISTER", style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(onClick = {
                navController.navigate("login"){
                    popUpTo("signup"){inclusive=true}
                }
            }) {
                Text("Already have an account? Login", color = Color(0xFF61A72E))
            }
                }
        }
}