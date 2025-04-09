package com.example.mad_16424.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mad_16424.R

val categories = listOf("Cabernet Sauvignon", "Champagne", "Chardonnay", "Wine Gift Sets")

@Composable
fun CategorySlider(
    categories: List<String>,
    selectedIndex: Int,
    onCategorySelected: (Int) -> Unit
) {
    val primary_3 = colorResource(id = R.color.primary_3) // Move it here
    val unselectedColor = Color.White

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 8.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        itemsIndexed(categories) { index, category ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clickable { onCategorySelected(index) }
            ) {
                Text(
                    text = category,
                    color = if (index == selectedIndex) primary_3 else unselectedColor,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = if (index == selectedIndex) FontWeight.Bold else FontWeight.Normal
                )
                if (index == selectedIndex) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(primary_3, shape = RoundedCornerShape(2.dp))
                    )
                }
            }
        }
    }
}

