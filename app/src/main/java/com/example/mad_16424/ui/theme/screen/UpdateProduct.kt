package com.example.mad_16424.ui.theme.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun UpdateProduct(
    onCreateClick: (String, String, String, String, String, String, String, String, String, String, Double) -> Unit // Passes data back to parent
) {
    var wineName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var alcoholVolume by remember { mutableStateOf("") }
    var monthsAging by remember { mutableStateOf("") }
    var bottleVolume by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("")}
    var frenchOak by remember { mutableStateOf("") }
    var drinkTime by remember { mutableStateOf("")}
    var ratingText by remember { mutableStateOf("")}
    var rating by remember { mutableStateOf(0.0) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Add new product",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        OutlinedTextField(
            value = wineName,
            onValueChange = { wineName = it },
            label = { Text("Product Name *") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Product Description *") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 4
        )

        OutlinedTextField(
            value = alcoholVolume,
            onValueChange = { alcoholVolume = it },
            label = { Text("Alcohol by volume *") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = monthsAging,
            onValueChange = { monthsAging = it },
            label = { Text("Total months aging *") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = bottleVolume,
            onValueChange = { bottleVolume = it },
            label = { Text("The bottle volume *") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Price *") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("The date of the production *") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text("The quantity of wine *") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = frenchOak,
            onValueChange = { frenchOak = it },
            label = { Text("The percent of french oak *") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = drinkTime,
            onValueChange = { drinkTime = it },
            label = { Text("When to drink *") },
            modifier = Modifier.fillMaxWidth(),
        )

        OutlinedTextField(
            value = ratingText,
            onValueChange = {
                ratingText = it
                rating = it.toDoubleOrNull() ?: 0.0  // safely convert to Double
            },
            label = { Text("Wine rating *") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )


        Text("Product Photo", style = MaterialTheme.typography.bodyMedium)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color.LightGray, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text("Click to upload", style = MaterialTheme.typography.bodySmall)
        }

        Button(
            onClick = {
                onCreateClick(wineName, description, alcoholVolume, monthsAging,bottleVolume, price, date,quantity,
                    frenchOak, drinkTime, rating)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Update the product")
        }
    }
}
