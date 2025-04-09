package com.example.mad_16424.ui.theme.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_16424.model.Wine
import com.example.mad_16424.data.WineRepository
import kotlinx.coroutines.launch
import com.example.mad_16424.network.WineRequest

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
                        wineImage = wineDto.image,
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

    fun getWineById(wineEquipmentId: String) {
        getByIdResponse = GetByIdResponse.Loading
        val id = wineEquipmentId.toIntOrNull()
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
                    price = response.data.price.toDouble() ?: 0.0,
                    bottleVolume = response.data.bottleVolume.toInt() ?: 0,
                    date = response.data.date ?: "",
                    wineAge = response.data.wineAge.toInt() ?: 0,
                    quantity = response.data.quantity.toInt() ?: 0,
                    alcoholVolume = response.data.alcoholVolume.toDouble() ?: 0.0,
                    frenchOak = response.data.frenchOak.toDouble() ?: 0.0,
                    drinkTime = response.data.drinkTime ?: "",
                    rating = response.data.rating.toDouble() ?: 0.0,
                    wineImage = response.data.image,
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

    sealed class CreateResponse {
        data object Loading: CreateResponse()
        data class Success(val message: String): CreateResponse()
        data class Error(val message: String): CreateResponse()
    }
    var createResponseState by mutableStateOf<CreateResponse?>(null)
        private set

    fun createWine(equipment: WineRequest) {
        createResponseState = CreateResponse.Loading

        viewModelScope.launch {
            try {
                val response = repository.createWine(equipment)

                createResponseState = CreateResponse.Success(response.message)
            } catch (e: Exception) {
                val message = e.message
                if (message == null) {
                    createResponseState = null
                    return@launch
                }
                createResponseState = CreateResponse.Error(message)
            }
        }
    }

    sealed class UpdateResponse {
        data object Loading: UpdateResponse()
        data class Success(val message: String): UpdateResponse()
        data class Error(val message: String): UpdateResponse()
    }
    var updateResponse by mutableStateOf<UpdateResponse?>(null)

    fun updateByIdWine(wine: Wine) {
        updateResponse = UpdateResponse.Loading

        viewModelScope.launch {
            try {
                val response = repository.updateWineById(wine)
                Log.d("update_wine_response", response.message)
                updateResponse = UpdateResponse.Success(response.message)
            } catch (e: Exception) {
                val message = e.message
                if (message == null) {
                    updateResponse = null
                    return@launch
                }
                updateResponse = UpdateResponse.Error(message)
            }
        }
    }

    sealed class DeleteResponse {
        data object Loading: DeleteResponse()
        data class Success(val message: String): DeleteResponse()
        data class Error(val message: String): DeleteResponse()
    }
    var deleteResponse by mutableStateOf<DeleteResponse?>(null)
        private set

    fun deleteWineById(wineId: String) {
        deleteResponse = DeleteResponse.Loading

        val id = wineId.toIntOrNull()
        if (id == null) {
            Log.d("delete_wine_validation_error", "Invalid wine ID")
            return
        }

        viewModelScope.launch {
            try {
                val response = repository.deleteWineById(id)
                deleteResponse = DeleteResponse.Success(response.message)
            } catch (e: Exception) {
                val message = e.message
                if (message == null) {
                    deleteResponse = null
                    return@launch
                }
                deleteResponse = DeleteResponse.Error(message)
            }
        }
    }

}
