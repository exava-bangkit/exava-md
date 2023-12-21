@file:OptIn(ExperimentalMaterial3Api::class)

package com.exava.exava.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.exava.exava.data.preferences.TourismAuthPreferences
import com.exava.exava.data.preferences.dataStore
import com.exava.exava.data.repository.TourismAuthRepository
import com.exava.exava.data.viewmodel.TourismAuthViewModel
import com.exava.exava.data.viewmodel.factory.TourismAuthViewModelFactory
import com.exava.exava.ui.composable.LoginComposable
import com.exava.exava.ui.theme.ExavaTheme
import com.exava.exava.util.injection.TourismRepositoryInjection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : ComponentActivity() {
    private val viewModel by viewModels<TourismAuthViewModel> {
        TourismAuthViewModelFactory(TourismAuthRepository())
    }
    private val preferences by lazy {
        TourismAuthPreferences.getInstance(dataStore)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExavaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var loadingLogin by remember { mutableStateOf(false)}
                    LoginComposable(
                        loading = loadingLogin,
                        onLoginClick = { uField, pField ->
                            CoroutineScope(Dispatchers.IO).launch {
                                loadingLogin = true
                                viewModel.login(uField, pField)
                                    .onSuccess {
                                        withContext(Dispatchers.Main) {
                                            loadingLogin = false
                                            it.token?.let { it1 -> preferences.setAuthToken(it1) }
//                                            Toast.makeText(this@LoginActivity, it.token, Toast.LENGTH_SHORT).show()
                                            val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
                                            startActivity(intent)
                                            finish()
                                        }
                                    }
                                    .onFailure {
                                        withContext(Dispatchers.Main) {
                                            loadingLogin = false
                                            Toast.makeText(this@LoginActivity, it.message, Toast.LENGTH_SHORT).show()
                                        }
                                    }
                            }
                        },
                        onRegisterClick = {
                            toRegister()
                        }
                    )
                }
            }
        }
    }

    fun toRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview2() {
    ExavaTheme {
        LoginComposable(
            loading = false,
            onLoginClick = { uField, pField ->

            },
            onRegisterClick = {}
        )
    }
}