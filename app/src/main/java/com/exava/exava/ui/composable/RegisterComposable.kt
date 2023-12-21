@file:OptIn(ExperimentalMaterial3Api::class)

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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.exava.exava.R

@Composable
fun RegisterComposable(
    modifier: Modifier = Modifier,
    loading: Boolean,
    onRegister: (username: String, email: String, password: String) -> Unit,
    onLogin: () -> Unit
) {
    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var name by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    RegisterComposableStateless(
        modifier = modifier,
        name = name,
        username = username,
        password = password,
        email = email,
        loading = loading,
        onNameChange = {
            name = it
        },
        onUsernameChange = {
            username = it
        },
        onPasswordChange = {
            password = it
        },
        onEmailChange = {
            email = it
        },
        onRegister = onRegister,
        onLogin = onLogin
    )


}

@Composable
fun RegisterComposableStateless(
    modifier: Modifier,
    name: String,
    username: String,
    password: String,
    email: String,
    loading: Boolean,
    onNameChange: (String) -> Unit,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onRegister: (username: String, email: String, password: String) -> Unit,
    onLogin: () -> Unit,
) {
    val scrollState = rememberScrollState()
    var passwordVisible by remember {
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
        OutlinedTextField(name, onValueChange = onNameChange, label = { Text(text = "Name") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(email, onValueChange = onEmailChange, label = { Text(text = "Email") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(username, onValueChange = onUsernameChange, label = { Text(text = "Username") }, modifier = Modifier.fillMaxWidth())
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
            onClick = {
                onRegister(username, email, password)
            },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if (loading) CircularProgressIndicator(color = MaterialTheme.colorScheme.secondary) else Text("Register")
        }
        TextButton(
            onClick = onLogin,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Login")
        }
    }


}
