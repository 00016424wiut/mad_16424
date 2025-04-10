package com.example.mad_16424.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class WineRequest {
    @SerialName ("title")
    var wineName: String = ""
    @SerialName ("description")
    var description: String =""
    @SerialName ("age")
    var wineAge: Int = 0
    @SerialName ("size")
    var bottleVolume: Int = 0
    @SerialName ("price")
    var price: Double = 0.0
    @SerialName ("date")
    var date: String =""
    @SerialName ("integer_one")
    var quantity: Int = 0
    @SerialName ("double_one")
    var alcoholVolume: Double = 0.0
    @SerialName ("double_two")
    var frenchOak: Double = 0.0
    @SerialName ("type")
    var drinkTime: String = ""
    @SerialName ("double_three")
    var rating: Double = 0.0
}

