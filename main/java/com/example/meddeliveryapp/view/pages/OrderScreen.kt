package com.example.meddeliveryapp.view.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.meddeliveryapp.R
import com.example.meddeliveryapp.model.Order

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(
    modifier: Modifier= Modifier,
    navController: NavController,
//    viewModel: OrderViewModel = viewModel()
) {
    val backgroundColor = Color(0xFF61A72E)
    val lightGreen = Color(0xFFB2E59E)
    val white = Color(0xFFFFFFFF)

    Scaffold(
        containerColor = backgroundColor,
        topBar = {
            TopAppBar(
                title = { Text("Orders", color = Color.Black) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Black)
                    }
                },
                actions = {
                    Text(
                        text = "View All Orders",
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable {
                                navController.navigate("orderDetails")
                            }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = white)
            )
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Card(
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(6.dp),
                colors = CardDefaults.cardColors(containerColor = white),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Order ID: 3354654654526", fontWeight = FontWeight.Bold)
                    Text("Distance: 2.5 km away", color = Color.Gray, fontSize = 14.sp)

                    Spacer(modifier = Modifier.height(12.dp))
                    OrderLocation("Pickup Location", "123 Main St, Anytown", backgroundColor, lightGreen)
                    Spacer(modifier = Modifier.height(12.dp))
                    OrderLocation("Drop Location", "Raiganj, West Bengal", backgroundColor, lightGreen)

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(Modifier.weight(1f)) {
                            Text("Rishika Paul", fontWeight = FontWeight.Bold)
                            Text("9800203793", fontSize = 13.sp, color = Color.Gray)
                        }
                        Image(
                            painter = painterResource(id = R.drawable.dp),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(100.dp)
                                .fillMaxWidth(0.6f)
                                .clip(RoundedCornerShape(8.dp))
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    Text("2 Kilometers", color = backgroundColor, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedButton(
                            onClick = {
                                //viewModel.cancelOrder()
                            },
                            modifier = Modifier.weight(1f).padding(end = 8.dp),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
                        ) {
                            Text("Cancel")
                        }

                        Button(
                            onClick = {
//                                viewModel.acceptOrder(
//                                    onSuccess = {
//                                        navController.navigate("orderDetails")
//                                    },
//                                    onFailure = {
//                                        Log.e("Order", "Failed to accept order: ${it.message}")
//                                    }
//                                )
                            },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(containerColor = backgroundColor)
                        ) {
                            Text("Accept Order", color = white)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun OrderLocation(title: String, address: String, iconTint: Color, iconBg: Color) {
    Row(verticalAlignment = Alignment.Top) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(iconBg),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Place, contentDescription = title, tint = iconTint, modifier = Modifier.size(20.dp))
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(title, fontWeight = FontWeight.SemiBold)
            Text(address, fontSize = 13.sp, color = Color.Gray)
        }
    }
}
