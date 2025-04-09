package com.example.mad_16424.ui.theme.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mad_16424.model.Wine
import com.example.mad_16424.ui.theme.components.WineHeader
import com.example.mad_16424.ui.theme.components.WineInfoRow
import com.example.mad_16424.ui.theme.components.WineImage
import com.example.mad_16424.ui.theme.components.WineRecommendation
import com.example.mad_16424.ui.theme.components.OrderButton
import com.example.mad_16424.ui.theme.components.getWineImageResource


@Composable
fun WineDetailsScreen(wine: Wine) {
    Column(modifier = Modifier.padding(16.dp)) {
        WineHeader(name = wine.wineName, year = wine.wineAge.toString())
        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            WineInfoRow(
                oakPercent = wine.frenchOak.toInt(),
                alcoholVolume = wine.alcoholVolume
            )
            WineImage(
                imageRes = getWineImageResource(wine.wineName),
                description = wine.wineName
            )
        }
        WineRecommendation(recommendation = wine.drinkTime)
        OrderButton { /* TODO: Order click */ }
    }
}
