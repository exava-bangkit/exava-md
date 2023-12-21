package com.exava.exava.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.exava.exava.R
import com.exava.exava.data.preferences.TourismAuthPreferences
import com.exava.exava.data.preferences.dataStore
import com.exava.exava.ui.theme.ExavaTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class SplashScreenActivity : ComponentActivity() {
    private val preferences by lazy {
        TourismAuthPreferences.getInstance(dataStore)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
            .setKeepOnScreenCondition {
                true
            }
//        setContent {
//            ExavaTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting()
//                }
//            }
//        }
        runBlocking {
            delay(3000L)
            preferences.getAuthToken().first {
                if (it != "") {
                    val intent = Intent(this@SplashScreenActivity, DashboardActivity::class.java)
                    this@SplashScreenActivity.startActivity(intent)
                    finish()
                } else {
                    val intent = Intent(this@SplashScreenActivity, LoginActivity::class.java)
                    this@SplashScreenActivity.startActivity(intent)
                    finish()
                }
                true
            }
        }
    }
}

@Composable
fun Greeting(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.exava_logo), contentDescription = "Logo Exava")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ExavaTheme {
        Greeting()
    }
}