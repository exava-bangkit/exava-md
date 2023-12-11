@file:OptIn(ExperimentalMaterial3Api::class)

package com.exava.exava.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.exava.exava.ui.composable.LoginComposable
import com.exava.exava.ui.theme.ExavaTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExavaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginComposable(
                        onLoginClick = { uField, pField ->
                            toLogin(uField, pField)
                        },
                        onRegisterClick = {
                            toRegister()
                        }
                    )
                }
            }
        }
    }
    fun toLogin(username: String, password: String) {

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
            onLoginClick = { uField, pField ->

            },
            onRegisterClick = {}
        )
    }
}