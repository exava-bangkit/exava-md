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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.exava.exava.R

@Composable
fun LoginComposable(
    modifier: Modifier = Modifier,
    loading: Boolean,
    onLoginClick: (email: String, password: String) -> Unit,
    onRegisterClick: () -> Unit,

    ) {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    LoginComposableStateless(
        modifier = modifier,
        email = email,
        password = password,
        onEmailChange = {
            email = it
        },
        loading = loading,
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
    email: String,
    password: String,
    loading: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: (email: String, password: String) -> Unit,
    onRegisterClick: () -> Unit,
) {
    val scrollState = rememberScrollState()
    var passwordVisible by rememberSaveable {
        mutableStateOf(false)
    }
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
        OutlinedTextField(
            email,
            onValueChange = onEmailChange,
            label = { Text(text = "Email") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            password,
            onValueChange = onPasswordChange,
            label = { Text(text = "Password") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if(passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if(passwordVisible) "Hide Password" else "Show Password"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = description)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(16.dp))
        ElevatedButton(
            onClick = { onLoginClick(email, password) },
            modifier = Modifier
                .fillMaxWidth(),
            enabled = !loading
        ) {
            if (loading) CircularProgressIndicator(color = MaterialTheme.colorScheme.secondary) else Text("Login")
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
