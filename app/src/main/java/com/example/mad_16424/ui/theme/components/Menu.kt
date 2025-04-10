package com.example.mad_16424.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.mad_16424.R

@Composable
fun Menu(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.size(24.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Box(modifier = Modifier.size(8.dp).background(colorResource(R.color.primary_2), RoundedCornerShape(2.dp)))
            Box(modifier = Modifier.size(8.dp).background(colorResource(R.color.primary_2), RoundedCornerShape(2.dp)))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Box(modifier = Modifier.size(8.dp).background(colorResource(R.color.primary_2), RoundedCornerShape(2.dp)))
            Box(modifier = Modifier.size(8.dp).background(colorResource(R.color.primary_2), RoundedCornerShape(2.dp)))
        }
    }
}