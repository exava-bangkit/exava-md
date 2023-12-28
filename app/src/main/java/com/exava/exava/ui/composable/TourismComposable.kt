package com.exava.exava.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bumptech.glide.integration.compose.placeholder
import com.exava.exava.R
import com.exava.exava.data.model.Tourism
import com.exava.exava.data.network.body.TourismRating
import com.exava.exava.data.viewmodel.TourismViewModel
import com.exava.exava.ui.component.RatingCard
import com.exava.exava.util.injection.TourismRepositoryInjection

@Composable
fun TourismComposable(
    tourismId: Int,
    viewModel: TourismViewModel,
    onBackPressed: () -> Unit
) {
    val tourism by viewModel.itemTourism.observeAsState()
    val rating by viewModel.ratingTourism.observeAsState()
    var valueComment by rememberSaveable { mutableStateOf("")}

    TourismComposableStateless(
        tourism = tourism,
        valueComment = valueComment,
        rating = rating,
        onBackPressed = onBackPressed,
        onCommentChange = {
            valueComment = it
        }
    )
    viewModel.loadItemTourism(tourismId)
    viewModel.loadRating(tourismId)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TourismComposableStateless(
    modifier: Modifier = Modifier,
    tourism: Tourism?,
    rating: TourismRating?,
    valueComment: String,
    onBackPressed: () -> Unit,
    onCommentChange: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = tourism?.placeName ?: "")},
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
                .fillMaxSize()
                .verticalScroll(scrollState),
        ) {
            if (tourism != null ) {
                AsyncImage(model = "https://source.unsplash.com/random",
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    placeholder = ColorPainter(MaterialTheme.colorScheme.surfaceVariant),
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
                Spacer(modifier = Modifier.size(8.dp))
                Text(tourism.description, modifier = Modifier.padding(start = 16.dp))
                Spacer(modifier = Modifier.size(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = MaterialTheme.colorScheme.surfaceVariant)
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text("Rating", modifier = Modifier.padding(start = 16.dp))
                Spacer(modifier = Modifier.size(16.dp))
                if (rating != null) {
                    RatingCard(one = rating.jsonMember1.toString(), two = rating.jsonMember2.toString(), three = rating.jsonMember3.toString(), four = rating.jsonMember4.toString(), five = rating.jsonMember5.toString())
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = MaterialTheme.colorScheme.surfaceVariant)
                )
                Spacer(modifier = Modifier.size(16.dp))
                if(true == false) {
                    Text("Reviews", modifier = Modifier.padding(start = 16.dp))
                    Spacer(modifier = Modifier.size(16.dp))
                    OutlinedTextField(
                        value = valueComment,
                        onValueChange = {
                            onCommentChange(it)

                        },
                        minLines = 5,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text("Add Review")
                    }
                    (1..10).forEach {
                        OutlinedCard(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                            Row(
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.Top,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Image(
                                    imageVector = Icons.Default.AccountCircle,
                                    contentDescription = "avatar",
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape)
                                )
                                Spacer(modifier = Modifier.size(8.dp))
                                Column {
                                    Text("Lorem Ipsum", style = MaterialTheme.typography.labelLarge)
                                    Spacer(modifier = Modifier.size(4.dp))
                                    Text("Lorem Ipsum", style = MaterialTheme.typography.labelMedium)
                                }
                            }
                            Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp))
                        }
                    }

                }
            } else {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    CircularProgressIndicator()

                }
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun TourismComposablePreview() {
    TourismComposable(1, TourismViewModel(TourismRepositoryInjection.provideRepository(LocalContext.current)), onBackPressed = {})
}
