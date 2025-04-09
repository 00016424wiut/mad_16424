package com.example.mad_16424.ui.theme.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_16424.model.Wine
import com.example.mad_16424.network.WineApiServices
import com.example.mad_16424.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType

class MainViewModel : ViewModel() {
    private val _wineList = MutableStateFlow<List<Wine>>(emptyList())
    val wineList: StateFlow<List<Wine>> = _wineList.asStateFlow()

    private val api: WineApiServices

    init {
        val json = Json { ignoreUnknownKeys = true }
        val contentType = "application/json".toMediaType()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://wiutmadcw.uz/api/v1/")
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()

        api = retrofit.create(WineApiServices::class.java)

        fetchWines()
    }

    private fun fetchWines() {
        viewModelScope.launch {
            try {
                val response = api.getAll("00016424")
                val wines = response.data.map {
                    Wine(
                        wineName = it.name,
                        price = it.price,
                        wineImage = getWineImageResource(it.name),
                        id = TODO(),
                        description = TODO(),
                        wineAge = TODO(),
                        bottleVolume = TODO(),
                        date = TODO(),
                        quantity = TODO(),
                        alcoholVolume = TODO(),
                        frenchOak = TODO(),
                        drinkTime = TODO(),
                        rating = TODO()
                    )
                }
                _wineList.value = wines
            } catch (e: Exception) {
                Log.e("MainViewModel", "Failed to load wines: ${e.message}")
            }
        }
    }

    fun getWineImageResource(name: String): Int {
        return when (name.lowercase()) {
            "cabernet sauvignon" -> R.drawable.img
            "champagne" -> R.drawable.img_1
            else -> R.drawable.wine
        }
    }
}
