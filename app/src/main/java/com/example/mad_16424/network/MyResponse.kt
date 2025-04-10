package com.example.mad_16424.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class MyResponse (
    @SerialName(value = "code")
    val code: Int = 0,
    @SerialName(value = "status")
    val status: String = "",
    @SerialName(value = "message")
    val message: String = ""
)


@Serializable
data class WineRecord(
    val id: Int,
    @SerialName(value = "title")
    val name: String,
    @SerialName(value = "description")
    val description: String?,
    @SerialName(value = "age")
    val wineAge: String?,
    @SerialName(value = "size")
    val bottleVolume: String?,
    @SerialName(value = "price")
    val price: String?,
    @SerialName(value = "date")
    val date: String?,
    @SerialName(value = "integer_one")
    val quantity: String?,
    @SerialName(value = "double_one")
    val alcoholVolume: String?,
    @SerialName(value = "double_two")
    val frenchOak: String?,
    @SerialName(value = "type")
    val drinkTime: String?,
    @SerialName(value = "double_three")
    val rating: String?
)

@Serializable
data class AllWineListResponse(
    @SerialName(value = "data")
    val data: List<WineRecord> = emptyList()
): MyResponse()

@Serializable
data class WineResponse(
    @SerialName(value = "data")
    val data: WineRecord
): MyResponse()