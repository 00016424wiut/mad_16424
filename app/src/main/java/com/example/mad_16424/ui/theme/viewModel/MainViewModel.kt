package com.example.mad_16424.ui.theme.viewModel

import android.util.Base64DataException
import android.util.Log
import android.view.View
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_16424.model.Wine
import com.example.mad_16424.network.WineApiServices
import com.example.mad_16424.R
import com.example.mad_16424.data.WineRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType
import androidx.lifecycle.viewModelScope

class MainViewModel : ViewModel() {
    private val repository:  WineRepository = WineRepository()

    sealed class GetAllResponse{
        data object Loading: GetAllResponse()
        data class Success(val data: List<Wine>): GetAllResponse()
        data class Error(val message: String): GetAllResponse()
    }
    var getAllResponse by mutableStateOf<GetAllResponse?>(null)
        private set


    //GetAll logic
    fun getAllWine(){
        getAllResponse = GetAllResponse.Loading
        viewModelScope.launch {
            try {
                val response = repository.getAllWine()
                val wineList = response.data.map {wineDto ->
                    Wine(
                        id = wineDto.id,
                        wineName = wineDto.name,
                        description = wineDto.description ?: "",
                        price = wineDto.price.toDouble() ?: 0.0,
                        bottleVolume = wineDto.bottleVolume.toInt() ?: 0,
                        date = wineDto.date ?: "",
                        wineAge = wineDto.wineAge.toInt() ?: 0,
                        quantity = wineDto.quantity.toInt() ?: 0,
                        alcoholVolume = wineDto.alcoholVolume.toDouble() ?: 0.0,
                        frenchOak = wineDto.frenchOak.toDouble() ?: 0.0,
                        drinkTime = wineDto.drinkTime ?: "",
                        rating = wineDto.rating.toDouble() ?: 0.0,
                        wineImage = TODO(),
                    )}
                getAllResponse = GetAllResponse.Success(wineList)
            } catch (e: Exception){
                val message = e.message
                if (message == null){
                    getAllResponse = null
                    return@launch
                }
                getAllResponse = GetAllResponse.Error(message)
            }
        }
    }

    //GetById logic
    sealed class GetByIdResponse {
        data object Loading: GetByIdResponse()
        data class Success(val data: Wine): GetByIdResponse()
        data class Error(val message: String): GetByIdResponse()
    }
    var getByIdResponse by mutableStateOf<GetByIdResponse?>(null)
        private set

    fun getGymEquipmentById(gymEquipmentId: String) {
        getByIdResponse = GetByIdResponse.Loading
        val id = gymEquipmentId.toIntOrNull()
        if (id == null) {
            Log.d("get_wine_validation_error", "Invalid wine ID")
            return
        }

        viewModelScope.launch {
            try {
                val response = repository.getWineById(id)
                val wineDto = Wine(
                    id = response.data.id,
                    wineName = response.data.name,
                    description = response.data.description ?: "",
                    price = response.data.price?.toDouble() ?: 0.0,
                    bottleVolume = response.data.bottleVolume?.toInt() ?: 0,
                    date = response.data.date ?: "",
                    wineAge = response.data.wineAge?.toInt() ?: 0,
                    quantity = response.data.quantity.toInt() ?: 0,
                    alcoholVolume = response.data.alcoholVolume?.toDouble() ?: 0.0,
                    frenchOak = response.data.frenchOak?.toDouble() ?: 0.0,
                    drinkTime = response.data.drinkTime ?: "",
                    rating = response.data.rating?.toDouble() ?: 0.0,
                    wineImage = TODO(),
                )
                getByIdResponse = GetByIdResponse.Success(
                    wineDto
                )
            } catch (e: Exception) {
                val message = e.message
                if (message == null) {
                    getByIdResponse = null
                    return@launch
                }
                getByIdResponse = GetByIdResponse.Error(message)
            }
        }
    }

}



//    private val _wineList = MutableStateFlow<List<Wine>>(emptyList())
//    val wineList: StateFlow<List<Wine>> = _wineList.asStateFlow()
//
//    private val api: WineApiServices
//
//    init {
//        val json = Json { ignoreUnknownKeys = true }
//        val contentType = "application/json".toMediaType()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://wiutmadcw.uz/api/v1/")
//            .addConverterFactory(json.asConverterFactory(contentType))
//            .build()
//
//        api = retrofit.create(WineApiServices::class.java)
//
//        fetchWines()
//    }
//
//    private fun fetchWines() {
//        viewModelScope.launch {
//            try {
//                val response = api.getAll("00016424")
//                val wines = response.data.map {
//                    Wine(
//                        wineName = it.name,
//                        price = it.price,
//                        wineImage = getWineImageResource(it.name),
//                        id = TODO(),
//                        description = TODO(),
//                        wineAge = TODO(),
//                        bottleVolume = TODO(),
//                        date = TODO(),
//                        quantity = TODO(),
//                        alcoholVolume = TODO(),
//                        frenchOak = TODO(),
//                        drinkTime = TODO(),
//                        rating = TODO()
//                    )
//                }
//                _wineList.value = wines
//            } catch (e: Exception) {
//                Log.e("MainViewModel", "Failed to load wines: ${e.message}")
//            }
//        }
//    }
//
//    fun getWineImageResource(name: String): Int {
//        return when (name.lowercase()) {
//            "cabernet sauvignon" -> R.drawable.img
//            "champagne" -> R.drawable.img_1
//            else -> R.drawable.wine
//        }
//    }