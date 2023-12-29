package com.exava.exava.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController

@Composable
fun BottomNav(
    navController: NavHostController
) {

    BottomNavStateless(navController)
}
@Composable
fun BottomNavStateless(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        NavigationBarItem(
            selected = true,
            onClick = { navController.navigate("home") },
            icon = {
                Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
            },
            label = {
                Text(text = "Home")
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("profile") },
            icon = {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Profile")
            },
            label = {
                Text(text = "Profile")
            }
        )
    }

}

@Preview
@Composable
fun BottomNavPreview() {
    BottomNav(NavHostController(LocalContext.current))
}
