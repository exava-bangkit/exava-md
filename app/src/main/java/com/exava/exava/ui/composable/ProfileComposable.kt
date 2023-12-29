package com.exava.exava.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Output
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.exava.exava.data.network.body.TourismProfile

@Composable
fun ProfileComposable(
    modifier: Modifier = Modifier,
    profile: TourismProfile,
    onLogoutClick: () -> Unit
) {

    ProfileComposableStateless(
        modifier = modifier,
        profile = profile,
        onLogoutClick = onLogoutClick
    )

}

@Composable
private fun ProfileComposableStateless(
    modifier: Modifier,
    profile: TourismProfile,
    onLogoutClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.size(32.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(imageVector = Icons.Default.AccountCircle, contentDescription = "display picture", modifier = Modifier.size(150.dp))
            Spacer(modifier = Modifier.size(32.dp))
            Text(text = profile.username, style = MaterialTheme.typography.titleLarge)
        }
        Spacer(modifier = Modifier.size(32.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = MaterialTheme.colorScheme.surfaceVariant)
        )
//        Spacer(modifier = Modifier.size(32.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .clickable {
                    onLogoutClick()
                }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Icon(Icons.Default.Output, null)
                Spacer(modifier = Modifier.size(16.dp))
                Text("Logout")

            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun ProfileComposablePreview(
) {
    ProfileComposable(onLogoutClick = {}, profile = TourismProfile(2,"sdf","234"))

}
