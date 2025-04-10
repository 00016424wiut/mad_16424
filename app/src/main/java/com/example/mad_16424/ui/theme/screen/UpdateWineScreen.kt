package com.example.mad_16424.ui.theme.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mad_16424.R
import com.example.mad_16424.model.Wine
import com.example.mad_16424.network.WineRequest
import com.example.mad_16424.ui.theme.Destination
import com.example.mad_16424.ui.theme.Typography
import com.example.mad_16424.ui.theme.components.Loading
import com.example.mad_16424.ui.theme.components.Menu
import com.example.mad_16424.ui.theme.viewModel.MainViewModel

@Composable
fun UpdateWineScreen(
    wineId: String?,
    navController: NavController,
    viewModel: MainViewModel
) {
    val wineState = viewModel.getByIdResponseState
    val context = LocalContext.current

    LaunchedEffect(wineId) {
        if (wineId != null) {
           viewModel.getWineById(wineId)
        }
    }

    when (wineState) {
        is MainViewModel.GetByIdResponse.Loading -> Loading()
        is MainViewModel.GetByIdResponse.Error -> {
            Toast.makeText(
                context,
                wineState.message,
                Toast.LENGTH_SHORT
            ).show()
        }
        is MainViewModel.GetByIdResponse.Success -> {
            UpdateWineForm(
                wine = wineState.data,
                navController = navController,
                viewModel = viewModel
            )
        }
        null -> {}
    }
}

@Composable
private fun UpdateWineForm(
    wine: Wine,
    navController: NavController,
    viewModel: MainViewModel
) {
    val wineState = viewModel.updateResponseState

    var wineName by remember { mutableStateOf(wine.wineName) }
    var description by remember { mutableStateOf(wine.description) }
    var wineAge by remember { mutableStateOf(wine.wineAge.toString()) }
    var bottleVolume by remember { mutableStateOf(wine.bottleVolume.toString()) }
    var price by remember { mutableStateOf(wine.price.toString()) }
    var date by remember { mutableStateOf(wine.date) }
    var quantity by remember { mutableStateOf(wine.quantity.toString()) }
    var alcoholVolume by remember { mutableStateOf(wine.alcoholVolume.toString()) }
    var frenchOak by remember { mutableStateOf(wine.frenchOak.toString()) }
    var drinkTime by remember { mutableStateOf(wine.drinkTime) }
    var rating by remember { mutableStateOf(wine.rating.toString()) }

    val context = LocalContext.current
    when (wineState) {
        is MainViewModel.UpdateResponse.Loading -> Loading()
        is MainViewModel.UpdateResponse.Error -> {
            Toast.makeText(
                context,
                wineState.message,
                Toast.LENGTH_SHORT
            ).show()
            viewModel.resetUpdateState()
        }
        is MainViewModel.UpdateResponse.Success -> {
            Toast.makeText(
                context,
                wineState.message,
                Toast.LENGTH_SHORT
            ).show()
            viewModel.resetUpdateState()
            navController.navigate(Destination.Main.route)
        }
        null -> {}
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Menu(
                modifier = Modifier.clickable(onClick = {
                    navController.navigate(Destination.Main.route)
                })
            )
            Text(
                text = "Edit wine",
                color = colorResource(R.color.primary_2),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Text(
            text = "Product Name *",
            color = colorResource(R.color.primary_2)
        )
        OutlinedTextField(
            value = wineName,
            onValueChange = { wineName = it },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = colorResource(R.color.primary_2),
                unfocusedContainerColor = colorResource(R.color.primary_2)
            ),
            shape = CircleShape,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Product Description *",
            color = colorResource(R.color.primary_2)
        )
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            maxLines = 4,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = colorResource(R.color.primary_2),
                unfocusedContainerColor = colorResource(R.color.primary_2)
            ),
            shape = CircleShape,
            modifier = Modifier.fillMaxWidth(),
        )

        Text(
            text = "Alcohol by volume *",
            color = colorResource(R.color.primary_2)
        )
        OutlinedTextField(
            value = alcoholVolume,
            onValueChange = { alcoholVolume = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = colorResource(R.color.primary_2),
                unfocusedContainerColor = colorResource(R.color.primary_2)
            ),
            shape = CircleShape,
            modifier = Modifier.fillMaxWidth(),
        )

        Text(
            text = "Total months aging *",
            color = colorResource(R.color.primary_2)
        )
        OutlinedTextField(
            value = wineAge,
            onValueChange = { wineAge = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = colorResource(R.color.primary_2),
                unfocusedContainerColor = colorResource(R.color.primary_2)
            ),
            shape = CircleShape,
            modifier = Modifier.fillMaxWidth(),
        )

        Text(
            text = "The bottle volume *",
            color = colorResource(R.color.primary_2)
        )
        OutlinedTextField(
            value = bottleVolume,
            onValueChange = { bottleVolume = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = colorResource(R.color.primary_2),
                unfocusedContainerColor = colorResource(R.color.primary_2)
            ),
            shape = CircleShape,
            modifier = Modifier.fillMaxWidth(),
        )

        Text(
            text = "Price *",
            color = colorResource(R.color.primary_2)
        )
        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = colorResource(R.color.primary_2),
                unfocusedContainerColor = colorResource(R.color.primary_2)
            ),
            shape = CircleShape,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "The date of the production *",
            color = colorResource(R.color.primary_2)
        )
        OutlinedTextField(
            value = date,
            onValueChange = { date = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = colorResource(R.color.primary_2),
                unfocusedContainerColor = colorResource(R.color.primary_2)
            ),
            shape = CircleShape,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "The quantity of wine *",
            color = colorResource(R.color.primary_2)
        )
        OutlinedTextField(
            value = quantity,
            onValueChange = { quantity = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = colorResource(R.color.primary_2),
                unfocusedContainerColor = colorResource(R.color.primary_2)
            ),
            shape = CircleShape,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "The percent of french oak *",
            color = colorResource(R.color.primary_2)
        )
        OutlinedTextField(
            value = frenchOak,
            onValueChange = { frenchOak = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = colorResource(R.color.primary_2),
                unfocusedContainerColor = colorResource(R.color.primary_2)
            ),
            shape = CircleShape,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "When to drink *",
            color = colorResource(R.color.primary_2)
        )
        OutlinedTextField(
            value = drinkTime,
            onValueChange = { drinkTime = it },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = colorResource(R.color.primary_2),
                unfocusedContainerColor = colorResource(R.color.primary_2)
            ),
            shape = CircleShape,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Wine rating *",
            color = colorResource(R.color.primary_2)
        )
        OutlinedTextField(
            value = rating,
            onValueChange = {
                rating = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = colorResource(R.color.primary_2),
                unfocusedContainerColor = colorResource(R.color.primary_2)
            ),
            shape = CircleShape,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                val wineRequest = WineRequest()
                wineRequest.wineName = wineName
                wineRequest.description = description
                wineRequest.wineAge = wineAge.toIntOrNull() ?: 0
                wineRequest.bottleVolume = wineAge.toIntOrNull() ?: 0
                wineRequest.price = price.toDoubleOrNull() ?: 0.0
                wineRequest.date = date
                wineRequest.quantity = quantity.toIntOrNull() ?: 0
                wineRequest.alcoholVolume = alcoholVolume.toDoubleOrNull() ?: 0.0
                wineRequest.frenchOak = frenchOak.toDoubleOrNull() ?: 0.0
                wineRequest.drinkTime = drinkTime
                wineRequest.rating = rating.toDoubleOrNull() ?: 0.0
                viewModel.updateByIdWine(wine.id, wineRequest)
            },
            colors = ButtonDefaults.buttonColors(colorResource(R.color.primary_2)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Update",
                style = Typography.titleSmall,
                color = colorResource(R.color.primary_1)
            )
        }
    }
}