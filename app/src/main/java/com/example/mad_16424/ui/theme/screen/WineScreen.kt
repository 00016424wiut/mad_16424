package com.example.mad_16424.ui.theme.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mad_16424.R
import com.example.mad_16424.ui.theme.Destination
import com.example.mad_16424.ui.theme.Typography
import com.example.mad_16424.ui.theme.components.Loading
import com.example.mad_16424.ui.theme.components.Menu
import com.example.mad_16424.ui.theme.viewModel.MainViewModel


@Composable
fun WineScreen(
    navController: NavController,
    viewModel: MainViewModel = viewModel()
) {
    val wineState = viewModel.getAllResponseState
    val context = LocalContext.current
    var wineId by remember { mutableStateOf<Int?>(null) }
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getAllWine()
    }

    when (wineState) {
        is MainViewModel.GetAllResponse.Loading -> Loading()
        is MainViewModel.GetAllResponse.Error -> {
            Toast.makeText(
                context,
                wineState.message,
                Toast.LENGTH_SHORT
                ).show()
        }

        is MainViewModel.GetAllResponse.Success -> {
            val wineList = wineState.data
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .padding(24.dp)
            ) {
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Menu()
                        Button(
                            onClick = {
                                navController.navigate(Destination.Create.route)
                            },
                            colors = ButtonDefaults.buttonColors(colorResource(R.color.primary_2))
                        ) {
                            Text(
                                text = "Create",
                                style = Typography.titleSmall,
                                color = colorResource(R.color.primary_1)
                            )
                        }
                    }
                }
                items(wineList) { wine ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 12.dp)
                        ) {
                            RatingStars(wine.rating)
                            Text(
                                text = wine.wineName,
                                style = Typography.titleLarge,
                                color = colorResource(R.color.accent_1)
                            )
                            Text(
                                text = wine.description,
                                style = Typography.bodySmall,
                                color = colorResource(R.color.accent_1),
                                maxLines = 4,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "$${wine.price} / ${wine.bottleVolume} ml",
                                style = Typography.bodySmall,
                                color = colorResource(R.color.accent_1)
                            )
                            Row (
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = "Edit",
                                    style = Typography.bodyMedium.copy(
                                        textDecoration = TextDecoration.Underline,
                                        baselineShift = BaselineShift(0.2f)
                                    ),
                                    color = colorResource(R.color.accent_1),
                                    modifier = Modifier.clickable(
                                        onClick = {
                                            navController.navigate(
                                                "${Destination.Update.route}/${wine.id}"
                                            )
                                        }
                                    )
                                )
                                Text(
                                    text = "Delete",
                                    style = Typography.bodyMedium.copy(
                                        textDecoration = TextDecoration.Underline,
                                        baselineShift = BaselineShift(0.2f)
                                    ),
                                    color = colorResource(R.color.accent_1),
                                    modifier = Modifier.clickable(
                                        onClick = {
                                            wineId = wine.id
                                            showDialog = true
                                        }
                                    )
                                )
                            }
                        }
                        Image(
                            painter = painterResource(R.drawable.img),
                            contentDescription = wine.wineName,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                        )
                    }
                }
                item {
                    if (wineId != null && showDialog) {
                        DeleteDialog(
                            wineId = wineId!!,
                            handleDismiss = {
                                showDialog = false
                                wineId = null
                            },
                            viewModel
                        )
                    }
                }
            }
        }
        null -> {
            Text("Fetching wines...")
        }
    }
}

@Composable
fun RatingStars(value: Double) {
    val totalStars = 5
    val filledStars = value.toInt()
    Row {
        repeat(totalStars) { index ->
            val isFilled = index < filledStars
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Rating Star ${index + 1}",
                modifier = Modifier.size(24.dp),
                tint = if (isFilled)
                    colorResource(R.color.accent_1)
                else
                    colorResource(R.color.accent_1).copy(alpha = 0.3f)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteDialog(
    wineId: Int,
    handleDismiss: () -> Unit,
    viewModel: MainViewModel
) {
    val wineState = viewModel.deleteResponseState
    val context = LocalContext.current

    when (wineState) {
        is MainViewModel.DeleteResponse.Loading -> Loading()
        is MainViewModel.DeleteResponse.Error -> {
            handleDismiss()
            Toast.makeText(
                context,
                wineState.message,
                Toast.LENGTH_SHORT
            ).show()
            viewModel.resetDeleteState()
        }
        is MainViewModel.DeleteResponse.Success -> {
            handleDismiss()
            Toast.makeText(
                context,
                wineState.message,
                Toast.LENGTH_SHORT
            ).show()
            viewModel.resetDeleteState()
            viewModel.getAllWine()
        }
        null -> {}
    }

    BasicAlertDialog(
        onDismissRequest = handleDismiss
    ) {
        Surface(
            shape = ShapeDefaults.Large,
            color = colorResource(R.color.primary_2),
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
        ) {
            Column (
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = "Confirm Deletion",
                )
                Text(
                    text = "Are you sure you want to delete this wine? This action cannot be undone.",
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            viewModel.deleteWineById(wineId.toString())
                        },
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.primary_1))
                    ) {
                        Text(text = "Remove", color = colorResource(R.color.primary_2))
                    }
                    OutlinedButton(
                        onClick = {
                            handleDismiss()
                        }
                    ) {
                        Text("Cancel")
                    }
                }
            }
        }
    }
}



