package com.example.meddeliveryapp.view.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
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
import androidx.navigation.NavController
import com.example.meddeliveryapp.R

@Composable
fun HomePage(modifier: Modifier = Modifier, navController: NavController) {
    val backgroundColor = Color(0xFF61A72E)
    val cardColor = Color.White
    val textColor = Color.Black
    val lightGreen = Color(0xFFB2E59E)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(bottom = 16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.mediswift_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 26.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Hello, John! 👋",
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "Ready to deliver?",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )
            }

            Image(
                painter = painterResource(id = R.drawable.dp),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(2.dp, backgroundColor, CircleShape),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Quick Actions Section
        Column(modifier = Modifier.padding(horizontal = 26.dp)) {
            Text(
                text = "Quick Actions",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = backgroundColor
            )

            Spacer(modifier = Modifier.height(12.dp))

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    QuickActionCard(
                        title = "New Delivery",
                        icon = {
                            Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White, modifier = Modifier.size(30.dp))
                        },
                        bgColor = backgroundColor,
                        textColor = Color.White,
                        modifier = Modifier.weight(1f)
                    ) {
                        navController.navigate("orders")
                    }

                    QuickActionCard(
                        title = "Track Delivery",
                        icon = {
                            Icon(Icons.Default.Place, contentDescription = "Track", tint = backgroundColor, modifier = Modifier.size(30.dp))
                        },
                        bgColor = Color.White,
                        textColor = backgroundColor,
                        bordered = true,
                        modifier = Modifier.weight(1f)
                    ) {
                        navController.navigate("track")
                    }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    QuickActionCard(
                        title = "All Deliveries",
                        icon = {
                            Icon(Icons.Default.Menu, contentDescription = "Menu", tint = backgroundColor, modifier = Modifier.size(30.dp))
                        },
                        bgColor = Color.White,
                        textColor = backgroundColor,
                        bordered = true,
                        modifier = Modifier.weight(1f)
                    ) {
                        navController.navigate("orderdetails")
                    }

                    QuickActionCard(
                        title = "Support",
                        icon = {
                            Icon(Icons.Default.Info, contentDescription = "Support", tint = Color.White, modifier = Modifier.size(30.dp))
                        },
                        bgColor = backgroundColor,
                        textColor = Color.White,
                        modifier = Modifier.weight(1f)
                    ) {
                        navController.navigate("help")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Recent Orders Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 26.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Recent Orders",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )

            Text(
                text = "See All",
                color = backgroundColor,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.clickable {
                    navController.navigate("orderdetails")
                }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 26.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(sampleOrders) { order ->
                OrderCard(order, backgroundColor)
            }
        }
    }
}

@Composable
fun QuickActionCard(
    title: String,
    icon: @Composable () -> Unit,
    bgColor: Color,
    textColor: Color,
    bordered: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .height(100.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(bgColor)
            .then(
                if (bordered)
                    Modifier.border(2.dp, Color(0xFF61A72E), RoundedCornerShape(12.dp))
                else Modifier
            )
            .clickable { onClick() }
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            icon()
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = title, color = textColor, fontSize = 14.sp)
        }
    }
}

@Composable
fun OrderCard(order: OrderData, green: Color) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .width(220.dp)
            .height(160.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(order.title, fontWeight = FontWeight.Bold)
            Text("${order.timeAgo} • ${order.name}", fontSize = 12.sp, color = Color.Gray)

            Box(
                modifier = Modifier
                    .align(Alignment.End)
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        when (order.status) {
                            "Pending" -> Color.Gray
                            "Out for Delivery" -> Color(0xFFFF9800)
                            else -> green
                        }
                    )
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(order.status, color = Color.White, fontSize = 12.sp)
            }
        }
    }
}

// Sample Data
data class OrderData(val title: String, val timeAgo: String, val name: String, val status: String)

val sampleOrders = listOf(
    OrderData("Grocery Delivery", "2 hours ago", "Rishika Paul | 6:00 PM", "Completed"),
    OrderData("Grocery Delivery", "5 mins ago", "Rishika Paul | 7:00 PM", "Out for Delivery"),
    OrderData("Grocery Delivery", "1 hour ago", "Rishika Paul | 5:00 PM", "Pending")
)
