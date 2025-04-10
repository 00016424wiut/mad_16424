package com.example.mad_16424.ui.theme

sealed class Destination(val route: String) {
    data object Main: Destination("wine_list_screen")
    data object Detail: Destination("wine_detail_screen")
    data object Create: Destination("create_wine_scree")
    data object Update: Destination("update_product_screen")
}