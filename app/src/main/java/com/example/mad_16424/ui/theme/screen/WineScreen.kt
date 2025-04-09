package com.example.mad_16424.ui.theme.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.example.mad_16424.ui.theme.viewModel.MainViewModel
import com.example.mad_16424.ui.theme.components.WineCard
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun WineScreen(viewModel: MainViewModel = viewModel()) {
    val wineList by viewModel.wineList.collectAsState()

    LazyColumn {
        items(wineList) { wine ->
            WineCard(wine)
        }
    }
}
