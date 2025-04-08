package com.example.mad_16424.ui.theme.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.mad_16424.R

@Composable
fun WineImage(imageRes: Int, description: String) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = description,
        modifier = Modifier
            .height(180.dp)
            .clip(RoundedCornerShape(12.dp))
    )
}

fun getWineImageResource(wineName: String): Int {
    return when (wineName.lowercase()) {
        "merlot" -> R.drawable.img
        "pinot noir" -> R.drawable.img_1
        else -> R.drawable.wine
    }
}

