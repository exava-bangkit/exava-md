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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun TourismComposable() {

    TourismComposableStateless()
}

@Composable
private fun TourismComposableStateless(
    modifier: Modifier = Modifier
) {
    var scrollState = rememberScrollState()
    Column(
        modifier = modifier,
    ) {
        AsyncImage(model = "https://placehold.co/600x400", contentDescription = null, modifier = Modifier
            .fillMaxWidth()
            .height(248.dp))
        Text(text = "Lorem Ipsum", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(top = 16.dp, start = 16.dp))
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            modifier = Modifier.padding(start = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.Star, contentDescription = "star")
            Text("5")
            Spacer(modifier = Modifier.size(16.dp))
            Text("11 Reviews")
        }
        Text("Lorem ipsum...............................", modifier = Modifier.padding(start = 16.dp))
    }

}

@Preview(showSystemUi = true)
@Composable
private fun TourismComposablePreview() {
    TourismComposable()
}
