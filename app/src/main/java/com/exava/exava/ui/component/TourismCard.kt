@file:OptIn(ExperimentalMaterial3Api::class)

package com.exava.exava.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.exava.exava.R

@Composable
fun TourismCard(
    modifier: Modifier = Modifier,
    imageUrl: String,
    title: String,
    description: String,
    onCardClick: () -> Unit
) {
    TourismCardStateless(
        modifier = modifier,
        imageUrl = imageUrl,
        title = title,
        description = description,
        onCardClick = onCardClick
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun TourismCardStateless(
    modifier: Modifier = Modifier,
    imageUrl: String,
    title: String,
    description: String,
    onCardClick: () -> Unit
) {
    Card(
        onClick = onCardClick,
        modifier = modifier
            .fillMaxWidth()
//            .height(248.dp)
    ) {
//        AsyncImage(
//            model = imageUrl,
//            contentDescription = title,
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(232.dp)
//        )
        GlideImage(model = "https://source.unsplash.com/random", contentDescription = title, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxWidth().height(232.dp))
//        Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = title, modifier = Modifier.fillMaxWidth().height(232.dp), contentScale = ContentScale.Crop)
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = title, style = MaterialTheme.typography.labelLarge)
            Spacer(modifier = Modifier.size(16.dp))
            Text(text = description, style = MaterialTheme.typography.labelMedium)

        }


    }

}

@Preview
@Composable
private fun TourismCardPreview(
) {
    TourismCard(
        imageUrl = "https://placehold.co/600x400",
        title = "Lorem Ipsum",
        description = "Lorem Ipsum",
        onCardClick = {}
    )
}
