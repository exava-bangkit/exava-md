@file:OptIn(ExperimentalMaterial3Api::class)

package com.exava.exava.ui.composable

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
import androidx.compose.ui.unit.dp

@Composable
fun RegisterComposable(
    modifier: Modifier = Modifier,

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
    RegisterComposableStateless(
        modifier = modifier,
        name = name,
        username = username,
        password = password,
        onNameChange = {
            name = it
        },
        onUsernameChange = {
            username = it
        },
        onPasswordChange = {
            password = it
        }
    )


}

@Composable
fun RegisterComposableStateless(
    modifier: Modifier,
    name: String,
    username: String,
    password: String,
    onNameChange: (String) -> Unit,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
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
            Text("Exava")
        }
        OutlinedTextField(name, onValueChange = onNameChange, label = { Text(text = "Name") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(username, onValueChange = onUsernameChange, label = { Text(text = "Username") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(password, onValueChange = onPasswordChange, label = { Text(text = "Password") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.size(16.dp))
        ElevatedButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Register")
        }
        TextButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Login")
        }
    }


}
