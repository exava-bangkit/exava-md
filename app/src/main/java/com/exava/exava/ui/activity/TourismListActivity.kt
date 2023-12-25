package com.exava.exava.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.exava.exava.data.model.Category
import com.exava.exava.data.viewmodel.TourismViewModel
import com.exava.exava.data.viewmodel.factory.TourismViewModelFactory
import com.exava.exava.ui.activity.DashboardActivity.Companion.CATEGORY_NAME
import com.exava.exava.ui.activity.DashboardActivity.Companion.ID_CATEGORY
import com.exava.exava.ui.activity.ui.theme.ExavaTheme
import com.exava.exava.ui.composable.TourismListComposable
import com.exava.exava.util.injection.TourismRepositoryInjection

class TourismListActivity : ComponentActivity() {

    private val mViewModel by viewModels<TourismViewModel> {
        TourismViewModelFactory(TourismRepositoryInjection.provideRepository(this))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExavaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val mCatID = intent.getIntExtra(ID_CATEGORY, 0)+1
                    val mCat = intent.getStringExtra(CATEGORY_NAME)

                    mViewModel.loadListTourism()

                    val mItems by mViewModel.listTourism.observeAsState(initial = listOf())

                    TourismListComposable(
                        items = mItems.filter{ it.categoryId == mCatID},
                        mCategory = (Category(mCatID, mCat!!)),
                        onBackPressed = {
                            finish()
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
fun GreetingPreview3() {
    ExavaTheme {
        TourismListComposable(
            items = listOf(),
            mCategory = Category(0, "null"),
            onBackPressed = {},
            onCardClick = {}
        )
    }
}