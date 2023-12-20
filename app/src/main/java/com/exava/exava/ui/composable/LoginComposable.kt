package com.exava.exava.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.exava.exava.R

@Composable
fun LoginComposable(
    modifier: Modifier = Modifier,
    onLoginClick: (username: String, password: String) -> Unit,
    onRegisterClick: () -> Unit,

) {
    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    LoginComposableStateless(
        modifier = modifier,
        username = username,
        password = password,
        onUsernameChange = {
            username = it
        },
        onPasswordChange = {
            password = it
        },
        onLoginClick = { usernameField, passwordField ->
            onLoginClick(usernameField, passwordField)
        },
        onRegisterClick = onRegisterClick

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginComposableStateless(
    modifier: Modifier,
    username: String,
    password: String,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: (username: String, password: String) -> Unit,
    onRegisterClick: () -> Unit,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.logo_icon), contentDescription = "Logo Exava")
        }
        OutlinedTextField(username, onValueChange = onUsernameChange, label = { Text(text = "Username") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(password, onValueChange = onPasswordChange, label = { Text(text = "Password") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.size(16.dp))
        ElevatedButton(
            onClick = { onLoginClick(username, password) },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Login")
        }
        TextButton(
            onClick = onRegisterClick,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Register")
        }
    }
}
