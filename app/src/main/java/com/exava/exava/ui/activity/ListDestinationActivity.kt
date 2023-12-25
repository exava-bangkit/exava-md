package com.exava.exava.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.exava.exava.data.model.Category
import com.exava.exava.data.viewmodel.TourismViewModel
import com.exava.exava.data.viewmodel.factory.TourismViewModelFactory
import com.exava.exava.ui.activity.DashboardActivity.Companion.CATEGORY_ID
import com.exava.exava.ui.activity.DashboardActivity.Companion.CATEGORY_ITEM
import com.exava.exava.ui.activity.ui.theme.ExavaTheme
import com.exava.exava.ui.composable.ListDestinationComposable
import com.exava.exava.util.injection.TourismRepositoryInjection

class ListDestinationActivity : ComponentActivity() {

    private val mViewModel by viewModels<TourismViewModel> {
        TourismViewModelFactory(TourismRepositoryInjection.provideRepository(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!intent.hasExtra(CATEGORY_ITEM)) finish()


        this.setContent {
            ExavaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val mCatID = intent.getIntExtra(CATEGORY_ID, 0)
                    val mCat = intent.getStringExtra(CATEGORY_ITEM)

                    mViewModel.loadListTourism()

                    val mItems by mViewModel.listTourism.observeAsState(initial = listOf())

                    ListDestinationComposable(
                            items = mItems.filter{ it.categoryId == mCatID},
                            mCategory = (Category(mCatID, mCat!!)),
                            onBackPressed = {
                                finish()
                                Log.d(this.toString(), "araara kategory "+mCatID)
                            },
                        onCardClick = {
                            val intent = Intent(this, TourismActivity::class.java)
                            intent.putExtra(TOURISM_ITEM, it.id)
                            this.startActivity(intent)
                        }
                        )
                }

            }
        }
        onBackPressedDispatcher.addCallback {
            finishAffinity()
        }
    }

    companion object {
        const val TOURISM_ITEM = "tourism_item"
    }
}



@Preview(showBackground = true)
@Composable
fun LisDestinationComposablePreview() {
    ExavaTheme {
        ListDestinationComposable(
            items = listOf(),
            mCategory = Category(0, "null"),
            onBackPressed = {},
            onCardClick = {}
        )
    }
}

