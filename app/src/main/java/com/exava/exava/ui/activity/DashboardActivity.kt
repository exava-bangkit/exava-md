@file:OptIn(ExperimentalMaterial3Api::class)

package com.exava.exava.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import com.exava.exava.data.model.Tourism
import com.exava.exava.data.network.body.TourismProfile
import com.exava.exava.data.preferences.TourismAuthPreferences
import com.exava.exava.data.preferences.dataStore
import com.exava.exava.data.viewmodel.TourismViewModel
import com.exava.exava.data.viewmodel.factory.TourismViewModelFactory
import com.exava.exava.ui.activity.DashboardActivity.Companion.CATEGORY_NAME
import com.exava.exava.ui.activity.DashboardActivity.Companion.ID_CATEGORY
import com.exava.exava.ui.activity.DashboardActivity.Companion.TOURISM_ITEM
import com.exava.exava.ui.component.BottomNav
import com.exava.exava.ui.composable.HomeComposable
import com.exava.exava.ui.composable.ProfileComposable
import com.exava.exava.ui.theme.ExavaTheme
import com.exava.exava.util.injection.TourismRepositoryInjection
import kotlinx.coroutines.runBlocking

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
                    DashboardComposable(
                        viewModel = viewModel,
                        onSearchClick = {
                            val intent = Intent(this, TourismSearchActivity::class.java)
                            startActivity(intent)
                        }
                    )
                    viewModel.loadListTourism()
                    viewModel.loadProfile()
                }
            }
        }
        onBackPressedDispatcher.addCallback {
            finishAffinity()
        }
    }

    companion object {
        const val TOURISM_ITEM = "tourism_item"
        const val ID_CATEGORY = "id_category"
        const val CATEGORY_NAME = "category_name"
    }
}

@Composable
fun DashboardComposable(
    viewModel: TourismViewModel,
    onSearchClick: () -> Unit
) {
    val navController = rememberNavController()
    val items by viewModel.listTourism.observeAsState(initial = listOf())
    val profile by viewModel.profile.observeAsState()

    DashboardComposableStateless(
        navController = navController,
        items = items,
        profile = profile,
        onSearchClick = {
            onSearchClick()
        }
    )
}

@Composable
fun DashboardComposableStateless(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    items: List<Tourism>,
    profile: TourismProfile?,
    onSearchClick: () -> Unit,
) {

    Scaffold(
        modifier = modifier,
        bottomBar = { BottomNav(navController) }
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
                    onSearchClick = {
                        onSearchClick()
                    },
                    onCategoryClick = {
                        Log.d(this.toString(), "araara kategory "+it.nama)
                        val intent = Intent(context, TourismListActivity::class.java)
                        intent.putExtra(ID_CATEGORY, it.id)
                        intent.putExtra(CATEGORY_NAME, it.nama)
                        context.startActivity(intent)
                    },
                    items = items
                )
            }
            composable("profile") {

                val context = LocalContext.current
                if (profile != null) {
                    ProfileComposable(
                        profile = profile,
                        onLogoutClick = {
                            val pref = TourismAuthPreferences.getInstance(context.dataStore)
                            runBlocking {
                                pref.setAuthToken("")
                                val intent = Intent(context, LoginActivity::class.java)
                                (context as Activity).finish()
                                context.startActivity(intent)
                            }
                        }
                    )
                } else {
                    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator()
                    }
                }

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
            DashboardComposable(viewModel = TourismViewModel(TourismRepositoryInjection.provideRepository(
                LocalContext.current)), onSearchClick = {})
        }
    }

}