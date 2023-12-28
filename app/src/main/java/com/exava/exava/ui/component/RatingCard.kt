package com.exava.exava.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RatingCard(
    modifier: Modifier = Modifier,
    one: String,
    two: String,
    three: String,
    four: String,
    five: String
){
    RatingCardStateless(
        modifier = modifier,
        one, two, three, four, five
    )
}


@Composable
private fun RatingCardStateless(
    modifier: Modifier,
    one: String,
    two: String,
    three: String,
    four: String,
    five: String
){
    Box(
        modifier = modifier
            .padding(16.dp)
    ) {
        OutlinedCard(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(text = one, style = MaterialTheme.typography.labelLarge)
                Spacer(modifier = Modifier.size(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Icon(imageVector = Icons.Default.Star, contentDescription = "star")
                    Icon(imageVector = Icons.Default.Star, contentDescription = "star")
                    Icon(imageVector = Icons.Default.Star, contentDescription = "star")
                    Icon(imageVector = Icons.Default.Star, contentDescription = "star")
                    Icon(imageVector = Icons.Default.Star, contentDescription = "star")
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(text = two, style = MaterialTheme.typography.labelLarge)
                Spacer(modifier = Modifier.size(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Icon(imageVector = Icons.Default.Star, contentDescription = "star")
                    Icon(imageVector = Icons.Default.Star, contentDescription = "star")
                    Icon(imageVector = Icons.Default.Star, contentDescription = "star")
                    Icon(imageVector = Icons.Default.Star, contentDescription = "star")
                    Icon(imageVector = Icons.Outlined.StarOutline, contentDescription = "star")
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(text = three, style = MaterialTheme.typography.labelLarge)
                Spacer(modifier = Modifier.size(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Icon(imageVector = Icons.Default.Star, contentDescription = "star")
                    Icon(imageVector = Icons.Default.Star, contentDescription = "star")
                    Icon(imageVector = Icons.Default.Star, contentDescription = "star")
                    Icon(imageVector = Icons.Outlined.StarOutline, contentDescription = "star")
                    Icon(imageVector = Icons.Outlined.StarOutline, contentDescription = "star")
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(text = four, style = MaterialTheme.typography.labelLarge)
                Spacer(modifier = Modifier.size(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Icon(imageVector = Icons.Default.Star, contentDescription = "star")
                    Icon(imageVector = Icons.Default.Star, contentDescription = "star")
                    Icon(imageVector = Icons.Outlined.StarOutline, contentDescription = "star")
                    Icon(imageVector = Icons.Outlined.StarOutline, contentDescription = "star")
                    Icon(imageVector = Icons.Outlined.StarOutline, contentDescription = "star")
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(text = five, style = MaterialTheme.typography.labelLarge)
                Spacer(modifier = Modifier.size(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Icon(imageVector = Icons.Default.Star, contentDescription = "star")
                    Icon(imageVector = Icons.Outlined.StarOutline, contentDescription = "star")
                    Icon(imageVector = Icons.Outlined.StarOutline, contentDescription = "star")
                    Icon(imageVector = Icons.Outlined.StarOutline, contentDescription = "star")
                    Icon(imageVector = Icons.Outlined.StarOutline, contentDescription = "star")
                }
            }
        }
    }
}

@Preview
@Composable
private fun RatingCardPreview() {
    RatingCard(one = "1", two = "7", three = "3", four = "6",  five = "8")
}
