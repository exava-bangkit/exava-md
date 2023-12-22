@file:OptIn(ExperimentalMaterial3Api::class)

package com.exava.exava.ui.activity

import android.content.Intent
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.exava.exava.data.model.Tourism
import com.exava.exava.data.viewmodel.TourismViewModel
import com.exava.exava.data.viewmodel.factory.TourismViewModelFactory
import com.exava.exava.ui.activity.DashboardActivity.Companion.TOURISM_ITEM
import com.exava.exava.ui.component.BottomNav
import com.exava.exava.ui.component.TopNav
import com.exava.exava.ui.composable.HomeComposable
import com.exava.exava.ui.theme.ExavaTheme
import com.exava.exava.util.injection.TourismRepositoryInjection

class DashboardActivity: ComponentActivity() {

    private val viewModel by viewModels<TourismViewModel> {
        TourismViewModelFactory(TourismRepositoryInjection.provideRepository(this))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExavaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val items by viewModel.listTourism.observeAsState(initial = listOf())
                    DashboardComposable(items)
                    viewModel.loadListTourism()
                }
            }
        }
        onBackPressedDispatcher.addCallback {
            finishAffinity()
        }
    }

    companion object {
        const val TOURISM_ITEM = "tourism_item"
    }
}

@Composable
fun DashboardComposable(
    items: List<Tourism>
) {
    val navController = rememberNavController()


    DashboardComposableStateless(
        navController = navController,
        items = items
    )
}

@Composable
fun DashboardComposableStateless(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    items: List<Tourism>
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
                val context = LocalContext.current
                HomeComposable(
                    onCardClick = {
                        val intent = Intent(context, TourismActivity::class.java)
                        intent.putExtra(TOURISM_ITEM, it.id)
                        context.startActivity(intent)
                    },
                    items = items
                )
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
            DashboardComposable(items = listOf())
        }
    }

}