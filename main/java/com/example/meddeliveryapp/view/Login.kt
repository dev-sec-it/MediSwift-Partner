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
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meddeliveryapp.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun Login(navController: NavController){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var forgotPasswordDialogBox by remember { mutableStateOf(false) }

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

            Text("Log In", style= MaterialTheme.typography.headlineMedium, color = Color(0xFF61A72E))
            Spacer(modifier = Modifier.height(24.dp))

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
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))

            val context= LocalContext.current

            Button(
                onClick = {
                    Firebase.auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(
                                    context,
                                    "Login Successful!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                navController.navigate("home") {
                                    popUpTo("login") { inclusive = true }
                                }
                            } else{
                                Toast.makeText(context, task.exception?.message?:"Login Failed",
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF61A72E),
                    contentColor = Color(0xFFFFFFFF)
                ),
                modifier = Modifier.fillMaxWidth()){
                Text("LOG IN", style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (forgotPasswordDialogBox){
                var resetEmail by remember { mutableStateOf("") }
                var context= LocalContext.current

                AlertDialog(
                    title={Text("Forgot Password?")},
                    text={
                            OutlinedTextField(
                                value = resetEmail,
                                onValueChange = {resetEmail=it},
                                label = {Text("Write your registered Email")},
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth()
                            )
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            if (resetEmail.isNotBlank()){
                                Firebase.auth.sendPasswordResetEmail(resetEmail)
                                    .addOnCompleteListener { task->
                                        if (task.isSuccessful){
                                            Toast.makeText(context, "Check your mail to Reset Password",
                                                Toast.LENGTH_SHORT).show()
                                            forgotPasswordDialogBox=false
                                        }else{
                                            Toast.makeText(context,"Registered email not found", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                            }else{
                                Toast.makeText(context, "Please Enter your registered email", Toast.LENGTH_SHORT).show()
                            }
                        }) {
                            Text("Submit")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {forgotPasswordDialogBox=false}) {
                            Text("Cancel")
                        }
                    },
                    onDismissRequest = {forgotPasswordDialogBox=false}
                )
            }


            TextButton(onClick = {
                forgotPasswordDialogBox=true
            }) {
                Text("Forgot Password?", color = Color(0xFF61A72E))
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(onClick = {
                navController.navigate("signup"){
                    popUpTo("login"){inclusive=true}
                }
            }) {
                Text("Don't have an account? Sign Up", color = Color(0xFF61A72E))
            }
        }
    }
}