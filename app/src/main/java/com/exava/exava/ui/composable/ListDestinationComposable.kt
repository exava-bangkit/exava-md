@file:OptIn(ExperimentalMaterial3Api::class)

package com.exava.exava.ui.composable

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.exava.exava.data.model.Category
import com.exava.exava.data.model.Tourism
import com.exava.exava.ui.activity.ListDestinationActivity
import com.exava.exava.ui.component.TourismCard

@Composable
fun ListDestinationComposable(
    items: List<Tourism>,
    mCategory: Category?,
    onBackPressed: () -> Unit,
    onCardClick: (Tourism) -> Unit,
) {
    ListDestinationComposableStateless(
        items = items,
        mCategory = mCategory,
        onBackPressed = onBackPressed,
        onCardClick = {
            onCardClick(it)
        }
    )

}

@ExperimentalMaterial3Api
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun ListDestinationComposableStateless(
    items: List<Tourism>,
    mCategory: Category?,
    onBackPressed: () -> Unit,
    onCardClick: (Tourism) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = ("List Kategori " + mCategory?.nama))},
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(Icons.Default.ArrowBack, "back")
                    }
                }
                )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            item {
                Text(
                    "List Pariwisata Dengan Kategori",
                    modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
                )
            }
            items(items) {
                Box(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 8.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    )
                ) {
                    TourismCard(
                        imageUrl = "https://placehold.co/600x400",
                        title = it.placeName,
                        description = it.description,
                        onCardClick = { onCardClick(it) })
                }
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun ListDestinationPreview() {
    ListDestinationComposable(items = listOf(),
        mCategory = Category(1, "null"), onBackPressed = {}, onCardClick = {})
}