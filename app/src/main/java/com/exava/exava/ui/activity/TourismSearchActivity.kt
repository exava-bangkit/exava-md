@file:OptIn(ExperimentalMaterial3Api::class)

package com.exava.exava.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.exava.exava.data.model.Tourism
import com.exava.exava.data.repository.TourismRepository
import com.exava.exava.data.viewmodel.TourismViewModel
import com.exava.exava.data.viewmodel.factory.TourismViewModelFactory
import com.exava.exava.ui.component.TourismCard
import com.exava.exava.ui.theme.ExavaTheme
import com.exava.exava.util.injection.TourismRepositoryInjection

class TourismSearchActivity(): ComponentActivity() {
    private val viewModel by viewModels<TourismViewModel> {
        TourismViewModelFactory(TourismRepositoryInjection.provideRepository(this))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExavaTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    TourismSearchComposable(
                        viewModel = viewModel,
                        onSearch = {

                        },
                        onSearchCardClick = {

                        }
                    )
                }
            }

        }
    }
}

@Composable
fun TourismSearchComposable(
    modifier: Modifier = Modifier,
    viewModel: TourismViewModel,
    onSearchCardClick: (Tourism) -> Unit,
    onSearch: (String) -> Unit
) {
    val items by viewModel.listTourism.observeAsState(initial = listOf())
    var searchString by rememberSaveable {
        mutableStateOf("")
    }

    TourismSearchComposableStateless(
        searchQuery = searchString,
        searchList = items,
        onSearchQueryChange = {
            searchString = it
        },
        onSearchCardClick = {
            onSearchCardClick(it)
        },
        onSearch = {
           onSearch(it)
        }
    )


}
@Composable
private fun TourismSearchComposableStateless(
    modifier: Modifier = Modifier,
    searchQuery: String,
    searchList: List<Tourism>,
    onSearchQueryChange: (String) -> Unit,
    onSearchCardClick: (Tourism) -> Unit,
    onSearch: (String) -> Unit,
) {
    SearchBar(
        query = searchQuery,
        onQueryChange = onSearchQueryChange,
        onSearch = {onSearch(it)},
        active = true,
        placeholder = { Text(text = "Cari pariwisata")},
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "cari", tint = MaterialTheme.colorScheme.onSurface)
        },
        onActiveChange = {},
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(searchList) {
                    Box(modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)) {
                        TourismCard(imageUrl = "https://placehold.co/600x400", title = it.placeName, description = it.description, onCardClick = {onSearchCardClick(it)})
                    }
                }
            }
        }
    )


}

@Preview(showSystemUi = true)
@Composable
private fun TourismSearchComposablePreview() {
    ExavaTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            TourismSearchComposable(
                viewModel = TourismViewModel(TourismRepository("sdf")),
                onSearch = {

                },
                onSearchCardClick = {

                }
            )
        }
    }

}
