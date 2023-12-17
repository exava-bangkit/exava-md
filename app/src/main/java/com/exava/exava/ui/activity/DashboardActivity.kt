@file:OptIn(ExperimentalMaterial3Api::class)

package com.exava.exava.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.exava.exava.ui.component.BottomNav
import com.exava.exava.ui.component.TopNav
import com.exava.exava.ui.composable.HomeComposable
import com.exava.exava.ui.theme.ExavaTheme

class DashboardActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExavaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DashboardComposable()
                }
            }
        }
    }
}

@Composable
fun DashboardComposable() {
    val navController = rememberNavController()


    DashboardComposableStateless(
        navController = navController
    )
}

@Composable
fun DashboardComposableStateless(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    Scaffold(
        modifier = modifier,
        bottomBar = { BottomNav() }
    ) {paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = modifier
                .padding(paddingValues)
        ) {
            composable("home") {
                HomeComposable()
            }
            composable("profile") {

            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DashboardComposablePreview() {
    ExavaTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            DashboardComposable()
        }
    }

}