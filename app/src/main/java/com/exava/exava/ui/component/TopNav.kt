@file:OptIn(ExperimentalMaterial3Api::class)

package com.exava.exava.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TopNav(
    placeholder: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit
) {
    var query by remember { mutableStateOf("") }
    val active by remember { mutableStateOf(false) }

    TopNavStateless(
        query = query,
        onQueryChange = {
            query = it

        },
        onSearch = {

        },
        active = active,
        onActiveChange = {

        },
        placeholder = placeholder,
        modifier = modifier,
        onSearchClick = {
            onSearchClick()
        }
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
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit,
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        active = active,
        onActiveChange = onActiveChange,
        placeholder = placeholder,
        modifier = modifier
            .clickable {
                onSearchClick()

            },
        ) {
    }
}

@Preview
@Composable
private fun TopNavPreview() {
    TopNav(
        placeholder = {
            Text("Cari tempat destinasi")
        },
        onSearchClick = {}
    )
}
