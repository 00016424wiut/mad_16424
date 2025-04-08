package com.example.mad_16424.ui.theme.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@Composable
fun WineHeader(name: String, year: String) {
    Text(
        text = "$year $name",
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Bold
    )
}
