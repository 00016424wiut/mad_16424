package com.example.mad_16424.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.sql.Date
import java.text.SimpleDateFormat

@Serializable
open class MyResponse () {
    @SerialName(value = "code")
    val code: Int = 0
    @SerialName(value = "status")
    val status: String = ""
    @SerialName(value = "message")
    val message: String = ""
}

@Serializable
data class WineRecord(
    val id: Int,
    @SerialName(value = "title")
    val name: String,
    @SerialName(value = "description")
    val description: String?,
    @SerialName(value = "age")
    val wineAge: Int,
    @SerialName(value = "size")
    val bottleVolume: Int,
    @SerialName(value = "price")
    val price: Double,
    @SerialName(value = "date")
    val date: String,
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