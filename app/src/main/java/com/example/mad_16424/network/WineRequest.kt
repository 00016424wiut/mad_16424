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
// need to ask
@Serializable
data class WineCategory(
    @SerialName(value = "id")
    val id: Int,
    @SerialName(value = "record_id")
    val recordId: String,
    @SerialName(value = "value")
    val value: String
)

@Serializable
open class Response(
    @SerialName(value = "code")
    val code: Int = 0,
    @SerialName(value = "status")
    val status: String = "",
    @SerialName(value = "message")
    val message: String = ""
)

@Serializable
data class AllWineResponse(
    @SerialName(value = "data")
    val data: List<WineRequest> = emptyList()
): Response()

@Serializable
data class AWineResponse(
    @SerialName(value = "data")
    val data: WineRequest
): Response()

