package com.example.meddeliveryapp.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.example.meddeliveryapp.view.pages.HomePage
import com.example.meddeliveryapp.view.pages.NotificationScreen
import com.example.meddeliveryapp.view.pages.OrderScreen
import com.example.meddeliveryapp.view.pages.Profile


@Composable
fun HomeScreen(modifier: Modifier=Modifier, navController: NavController) {

    val navItemList= listOf(
        NavItem("Home", Icons.Default.Home),
        NavItem("Notifications", Icons.Default.Notifications),
        NavItem("Orders", Icons.Default.ShoppingCart),
        NavItem("Profile", Icons.Default.Person)
    )

    var selectedIndexState = remember {
        mutableIntStateOf(0)
    }

    val selectedIndex by selectedIndexState

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF61A72E)
            ) {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = index == selectedIndex,
                        onClick={
                            selectedIndexState.intValue=index
                        },
                        icon={
                            Icon(imageVector = navItem.icon, contentDescription = navItem.label)
                        },
                        label={
                            Text(text = navItem.label )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White,
                            unselectedIconColor = Color.White.copy(alpha = 0.7f),
                            selectedTextColor = Color.White,
                            unselectedTextColor = Color.White.copy(alpha = 0.7f),
                            indicatorColor = Color(0xFF2E7D32)
                        )
                  )
                }
            }
        }
    ) {
        ContentScreen(
            modifier = modifier.padding(it),
            selectedIndex=selectedIndex,
            navController= navController)
    }
}
@Composable
fun ContentScreen(modifier: Modifier= Modifier,
                  selectedIndex: Int,
                  navController: NavController){
    when(selectedIndex){
        0 -> HomePage(
            modifier,
            navController = navController
        )
        1 -> NotificationScreen(modifier= modifier,
            navController = navController)
        2 -> OrderScreen(
            modifier = modifier,
            navController = navController)
        3 -> Profile(
            modifier = modifier,
            navController = navController,
            onDeleteAccount = { /* your logic */ }
        )
    }
}

data class NavItem(
    val label: String,
    val icon: ImageVector
)