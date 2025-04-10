package com.example.mad_16424.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import androidx.navigation.navArgument
import com.example.mad_16424.R
import com.example.mad_16424.ui.theme.screen.CreateWineScreen
import com.example.mad_16424.ui.theme.screen.UpdateWineScreen
import com.example.mad_16424.ui.theme.screen.WineScreen
import com.example.mad_16424.ui.theme.viewModel.MainViewModel

@Composable
fun WineAppNavigation() {
    val navController = rememberNavController()
    val wineViewModel: MainViewModel = viewModel()
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
    ) {
            innerPadding ->
        val navGraph = navController.createGraph(
            startDestination = Destination.Main.route
        ) {
            composable(Destination.Main.route) {
                WineScreen(
                    navController = navController,
                    viewModel = wineViewModel
                )
            }
            composable(Destination.Create.route) {
                CreateWineScreen(
                    navController,
                    viewModel = wineViewModel
                )
            }
            composable(
                route = Destination.Update.route + "/{id}",
                arguments = listOf(navArgument("id") { type = NavType.StringType })
            ) {
                val wineId = it.arguments?.getString("id")
                UpdateWineScreen(wineId, navController, wineViewModel)
            }
        }
        NavHost(
            navController = navController,
            graph = navGraph,
            modifier = Modifier
                .padding(innerPadding)
                .background(colorResource(R.color.primary_1))
                .fillMaxSize()
        )
    }
}