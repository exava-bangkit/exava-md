package com.exava.exava.ui.activity

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.exava.exava.data.model.Tourism
import com.exava.exava.data.viewmodel.TourismViewModel
import com.exava.exava.data.viewmodel.factory.TourismViewModelFactory
import com.exava.exava.ui.activity.DashboardActivity.Companion.TOURISM_ITEM
import com.exava.exava.ui.composable.TourismComposable
import com.exava.exava.ui.theme.ExavaTheme
import com.exava.exava.util.injection.TourismRepositoryInjection

class TourismActivity : ComponentActivity() {
    private val viewModel by viewModels<TourismViewModel> {
        TourismViewModelFactory(TourismRepositoryInjection.provideRepository(this))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!intent.hasExtra(TOURISM_ITEM)) finish()
        val item = intent.getIntExtra(TOURISM_ITEM, 0)
        if (item == 0) finish()
        setContent {
            ExavaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surfaceVariant
                ) {
                    val tourism by viewModel.itemTourism.observeAsState(initial = null)
                    TourismComposable(
                        tourism,
                        onBackPressed = {
                            finish()
                        }
                    )
                    viewModel.loadItemTourism(item)
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TourismActivityPreview() {
    ExavaTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.surfaceVariant
        ) {
            TourismComposable(
                tourism = Tourism(1, "sdasd", "asdfasdf", 1,2,34343,5,23.3,343.3, coordinate = "asdf"),
                onBackPressed = {}
            )

        }
    }

}