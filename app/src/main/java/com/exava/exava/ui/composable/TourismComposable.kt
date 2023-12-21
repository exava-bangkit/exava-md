package com.exava.exava.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.exava.exava.data.model.Tourism

@Composable
fun TourismComposable(
    tourism: Tourism,
    onBackPressed: () -> Unit
) {

    TourismComposableStateless(
        tourism = tourism,
        onBackPressed = onBackPressed
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TourismComposableStateless(
    modifier: Modifier = Modifier,
    tourism: Tourism,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = tourism.placeName)},
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(Icons.Default.ArrowBack, "back")
                    }
                }
            )
        }
    ) {
        var scrollState = rememberScrollState()
        Column(
            modifier = modifier
                .padding(it)
                .verticalScroll(scrollState),
        ) {
            AsyncImage(model = "https://source.unsplash.com/random",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                .fillMaxWidth()
                .height(248.dp))
            Text(text = tourism.placeName, style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(top = 16.dp, start = 16.dp))
            Spacer(modifier = Modifier.size(16.dp))
            Row(
                modifier = Modifier.padding(start = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.Star, contentDescription = "star")
                Text(tourism.rating.toString())
                Spacer(modifier = Modifier.size(16.dp))
                Text("11 Reviews")
            }
            Text(tourism.description, modifier = Modifier.padding(start = 16.dp))
        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun TourismComposablePreview() {
    TourismComposable(Tourism(1, "sdasd", "asdfasdf", 1,2,34343,5,23.3,343.3, coordinate = "asdf"), onBackPressed = {})
}
