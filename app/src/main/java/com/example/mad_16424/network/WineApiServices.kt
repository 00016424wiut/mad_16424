package com.example.mad_16424.network


import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query


private const val BASE_URL =
    "https://wiutmadcw.uz/api/v1/"

private val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
    coerceInputValues = true
}


private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

private const val STUDENT_ID = "00016424"


interface  WineApiServices {
    @GET("records/all")
    suspend fun getAll(
        @Query("student_id") studentId: String = STUDENT_ID
    ): AllWineListResponse
    @GET("records/{record_id}")
    suspend fun getOneById(
        @Path("record_id") id: Int,
        @Query("student_id") studentId: String = STUDENT_ID
    ): WineResponse
    @POST("records")
    suspend fun createOne(
        @Body request: WineRequest,
        @Query("student_id") studentId: String = STUDENT_ID
    ): MyResponse
    @DELETE("records/{record_id}")
    suspend fun deleteOneById(
        @Path("record_id") id: Int,
        @Query("student_id") studentId: String = STUDENT_ID
    ): MyResponse
    @PUT("records/{record_id}")
    suspend fun updateOneById(
        @Path("record_id") id: Int,
        @Body request: WineRequest,
        @Query("student_id") studentId: String = STUDENT_ID
    ): MyResponse

    }
object WineApi {
    val retrofitService: WineApiServices by lazy {
        retrofit.create(WineApiServices::class.java)
    }
}