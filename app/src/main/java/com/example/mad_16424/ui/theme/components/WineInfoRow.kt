package com.example.mad_16424.ui.theme.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text



@Composable
fun WineInfoRow(
    oakPercent: Int,
    alcoholVolume: Double
) {
    Column {
        InfoItem(label = "French oak for 13 months", value = "$oakPercent%")
        InfoItem(label = "Alcohol by volume", value = "$alcoholVolume%")
    }
}

@Composable
fun InfoItem(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(text = label, style = MaterialTheme.typography.bodySmall)
        Text(text = value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
    }
}
