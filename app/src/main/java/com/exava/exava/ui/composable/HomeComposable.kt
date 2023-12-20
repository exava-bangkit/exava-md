package com.exava.exava.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.exava.exava.data.model.Tourism
import com.exava.exava.data.model.getAllCategory
import com.exava.exava.ui.component.TopNav
import com.exava.exava.ui.component.TourismCard

@Composable
fun HomeComposable(
    modifier: Modifier = Modifier,
    onCardClick: (Tourism) -> Unit,
) {
    
    HomeComposableStateless(
        onCardClick = {
            onCardClick(it)
        }
    )
    
}

@Composable
private fun HomeComposableStateless(
    modifier: Modifier = Modifier,
    onCardClick: (Tourism) -> Unit,
) {
    val mockList = listOf(
        Tourism(
            placeName = "Lorem Ipsum",
            description = "lorem ipsum",
            categoryId = 1,
            cityId = 1,
            price = 100000,
            rating = 5,
            lat = 66.6,
            long = 66.6
        ),
        Tourism(
            placeName = "Lorem Ipsum",
            description = "lorem ipsum",
            categoryId = 1,
            cityId = 1,
            price = 100000,
            rating = 5,
            lat = 66.6,
            long = 66.6
        ),
        Tourism(
            placeName = "Lorem Ipsum",
            description = "lorem ipsum",
            categoryId = 1,
            cityId = 1,
            price = 100000,
            rating = 5,
            lat = 66.6,
            long = 66.6
        ),
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                TopNav(
                    placeholder = { Text(text = "Cari destinasi") },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            LazyRow(
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterHorizontally)

            ) {
                items(getAllCategory()) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .height(72.dp)
                            .width(64.dp)
                    ) {
                        Icon(imageVector = Icons.Outlined.LocationOn, contentDescription = it.nama, modifier = Modifier.size(40.dp))
                        Text(it.nama, maxLines = 2, fontSize = 10.sp, textAlign = TextAlign.Center, lineHeight = 14.sp)
                    }
                }
            }

        }
        item {
            Text("Rekomendasi Pariwisata", modifier = Modifier.padding(start = 16.dp, bottom = 8.dp))
        }
        items(mockList) {
            Box(modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)) {
                TourismCard(imageUrl = "https://placehold.co/600x400", title = it.placeName, description = it.description, onCardClick = {onCardClick(it)})
            }
        }
    }
    
}

@Preview(showSystemUi = true)
@Composable
private fun HomeComposablePreview() {
    HomeComposable(onCardClick = {})
}
