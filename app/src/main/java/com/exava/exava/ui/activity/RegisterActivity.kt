package com.exava.exava.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.exava.exava.data.repository.TourismAuthRepository
import com.exava.exava.data.viewmodel.TourismAuthViewModel
import com.exava.exava.data.viewmodel.factory.TourismAuthViewModelFactory
import com.exava.exava.ui.composable.RegisterComposable
import com.exava.exava.ui.theme.ExavaTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterActivity: ComponentActivity() {
    private val viewModel by viewModels<TourismAuthViewModel> {
        TourismAuthViewModelFactory(TourismAuthRepository())
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
                    var loadingRegister by remember {
                        mutableStateOf(false)
                    }
                    RegisterComposable(
                        loading = loadingRegister,
                        onRegister = { username, email, password ->
                            CoroutineScope(Dispatchers.IO).launch {
                                loadingRegister = true
                                viewModel.register(username, email, password)
                                    .onSuccess {
                                        withContext(Dispatchers.Main) {
                                            loadingRegister = false
                                            Toast.makeText(
                                                this@RegisterActivity,
                                                it.message,
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            val intent = Intent(
                                                this@RegisterActivity,
                                                LoginActivity::class.java
                                            )
                                            startActivity(intent)
                                            finish()
                                        }
                                    }
                                    .onFailure {
                                        withContext(Dispatchers.Main) {
                                            loadingRegister = false
                                            Toast.makeText(
                                                this@RegisterActivity,
                                                it.message,
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                            }
                        },
                        onLogin = {
                            if (!this.isTaskRoot) {
                                finish()
                            }else {
                                val intent = Intent(this, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                        }
                    )
                }
            }

        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterActivityPreview() {
    ExavaTheme {
        RegisterComposable(
            loading = false,
            onRegister = { username, email, password ->

            },
            onLogin = {

            }
        )
    }
}
