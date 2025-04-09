package com.example.mad_16424.ui.theme.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mad_16424.R
import com.example.mad_16424.ui.theme.viewModel.MainViewModel
import com.example.mad_16424.ui.theme.components.WineCard
import com.example.mad_16424.ui.theme.components.RemoveItemDialog
import com.example.mad_16424.model.Wine

@Composable
fun WineScreen(
    navController: NavController,
    viewModel: MainViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getAllWine()
    }

    val wineState = viewModel.getAllResponse
    var showDialog by remember { mutableStateOf(false) }
    var selectedWine by remember { mutableStateOf<Wine?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.primary_1))
    ){
        when (wineState) {
            is MainViewModel.GetAllResponse.Loading -> {
                Text("Loading...")
            }

            is MainViewModel.GetAllResponse.Error -> {
                Text("Error: ${wineState.message}")
            }

            is MainViewModel.GetAllResponse.Success -> {
                val wineList = wineState.data

                LazyColumn {
                    items(wineList) { wine ->
                        WineCard(
                            wine = wine,
                            onWineClicked = {
                                navController.navigate("wineDetails/${wine.id}")
                            },
                            onEditClicked = {
                                navController.navigate("updateWine/${wine.id}")
                            },
                            onRemoveClicked = {
                                selectedWine = wine
                                showDialog = true
                            }
                        )
                    }
                }

                if (showDialog && selectedWine != null) {
                    RemoveItemDialog(
                        wineName = selectedWine!!.wineName,
                        onConfirm = {
                            viewModel.deleteWineById(selectedWine!!.id.toString())
                            showDialog = false
                        },
                        onCancel = {
                            showDialog = false
                        }
                    )
                }
            }

            null -> {
                // Initial state
                Text("Fetching wines...")
            }
        }
    }

}