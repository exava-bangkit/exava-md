@file:OptIn(ExperimentalMaterial3Api::class)

package com.exava.exava.ui.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TopNav(
    placeholder: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    val query by remember { mutableStateOf("") }
    val active by remember { mutableStateOf(false) }

    TopNavStateless(
        query = query,
        onQueryChange = {

        },
        onSearch = {

        },
        active = active,
        onActiveChange = {

        },
        placeholder = placeholder,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopNavStateless(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    active: Boolean,
    onActiveChange: (Boolean) -> Unit,
    placeholder: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        active = active,
        onActiveChange = onActiveChange,
        placeholder = placeholder,
        modifier = modifier,
        ) {
    }
}

@Preview
@Composable
private fun TopNavPreview() {
    TopNav(
        placeholder = {
            Text("Cari tempat destinasi")
        }
    )
}
