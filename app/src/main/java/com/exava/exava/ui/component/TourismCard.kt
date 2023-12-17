@file:OptIn(ExperimentalMaterial3Api::class)

package com.exava.exava.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun TourismCard(
    modifier: Modifier = Modifier,
    imageUrl: String,
    title: String,
    description: String
) {
    TourismCardStateless(
        modifier = modifier,
        imageUrl = imageUrl,
        title = title,
        description = description
    )
}

@Composable
private fun TourismCardStateless(
    modifier: Modifier = Modifier,
    imageUrl: String,
    title: String,
    description: String
) {
    Card(
        onClick = { /*TODO*/ },
        modifier = modifier
            .fillMaxWidth()
            .height(248.dp)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = title,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(text = title)
        Text(text = description)


    }

}

@Preview
@Composable
private fun TourismCardPreview(
) {
    TourismCard(
        imageUrl = "https://placehold.co/600x400",
        title = "Lorem Ipsum",
        description = "Lorem Ipsum"
    )
}
