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

    sealed class GetAllResponse {
        data object Loading: GetAllResponse()
        data class Success(val data: List<Wine>): GetAllResponse()
        data class Error(val message: String): GetAllResponse()
    }
    var getAllResponseState by mutableStateOf<GetAllResponse?>(null)
        private set


    //Done!
    fun getAllWine () {
        getAllResponseState = GetAllResponse.Loading
        viewModelScope.launch {
            try {
                val response = repository.getAllWine()
                val wineList = response.data.map {
                   Wine(
                       id = it.id,
                       wineName = it.name,
                       description = it.description ?: "",
                       wineAge = it.wineAge?.toIntOrNull() ?: 0,
                       bottleVolume = it.bottleVolume?.toIntOrNull() ?: 0,
                       price = it.price?.toDoubleOrNull() ?: 0.0,
                       date = it.date ?: "",
                       quantity = it.quantity?.toIntOrNull() ?: 0,
                       alcoholVolume = it.alcoholVolume?.toDoubleOrNull() ?: 0.0,
                       frenchOak = it.frenchOak?.toDoubleOrNull() ?: 0.0,
                       drinkTime = it.drinkTime ?: "",
                       rating = it.rating?.toDoubleOrNull() ?: 0.0
                   )
                }
                getAllResponseState = GetAllResponse.Success(wineList)
            } catch (e: Exception){
                getAllResponseState =
                    if (e.message !== null ) GetAllResponse.Error(e.message!!)
                    else null
            }
        }
    }

    //In progress
    sealed class GetByIdResponse {
        data object Loading: GetByIdResponse()
        data class Success(val data: Wine): GetByIdResponse()
        data class Error(val message: String): GetByIdResponse()
    }
    var getByIdResponseState by mutableStateOf<GetByIdResponse?>(null)
        private set

    fun getWineById(wineEquipmentId: String) {
        getByIdResponseState = GetByIdResponse.Loading
        val id = wineEquipmentId.toIntOrNull() ?: return

        viewModelScope.launch {
            try {
                val response = repository.getWineById(id)
                val wine = Wine(
                    id = response.data.id,
                    wineName = response.data.name,
                    description = response.data.description ?: "",
                    wineAge = response.data.wineAge?.toIntOrNull() ?: 0,
                    bottleVolume = response.data.bottleVolume?.toIntOrNull() ?: 0,
                    price = response.data.price?.toDoubleOrNull() ?: 0.0,
                    date = response.data.date ?: "",
                    quantity = response.data.quantity?.toIntOrNull() ?: 0,
                    alcoholVolume = response.data.alcoholVolume?.toDoubleOrNull() ?: 0.0,
                    frenchOak = response.data.frenchOak?.toDoubleOrNull() ?: 0.0,
                    drinkTime = response.data.drinkTime ?: "",
                    rating = response.data.rating?.toDoubleOrNull() ?: 0.0
                )
                getByIdResponseState = GetByIdResponse.Success(wine)
            } catch (e: Exception) {
                getByIdResponseState =
                    if (e.message !== null ) GetByIdResponse.Error(e.message!!)
                    else null
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

    fun createWine(wineRequest: WineRequest) {
        createResponseState = CreateResponse.Loading

        viewModelScope.launch {
            try {
                val response = repository.createWine(wineRequest)
                createResponseState = CreateResponse.Success(response.message)
            } catch (e: Exception) {
                createResponseState =
                    if (e.message !== null ) CreateResponse.Error(e.message!!)
                    else null
            }
        }
    }

    fun resetCreateState() {
        createResponseState = null
    }

    sealed class UpdateResponse {
        data object Loading: UpdateResponse()
        data class Success(val message: String): UpdateResponse()
        data class Error(val message: String): UpdateResponse()
    }
    var updateResponseState by mutableStateOf<UpdateResponse?>(null)

    fun updateByIdWine(wineId: Int, wineRequest: WineRequest) {
        updateResponseState = UpdateResponse.Loading

        viewModelScope.launch {
            try {
                val response = repository.updateWineById(wineId, wineRequest)
                updateResponseState = UpdateResponse.Success(response.message)
            } catch (e: Exception) {
                updateResponseState =
                    if (e.message !== null ) UpdateResponse.Error(e.message!!)
                    else null
            }
        }
    }

    fun resetUpdateState() {
        updateResponseState = null
    }

    sealed class DeleteResponse {
        data object Loading: DeleteResponse()
        data class Success(val message: String): DeleteResponse()
        data class Error(val message: String): DeleteResponse()
    }
    var deleteResponseState by mutableStateOf<DeleteResponse?>(null)
        private set

    fun deleteWineById(wineId: String) {
        deleteResponseState = DeleteResponse.Loading

        val id = wineId.toIntOrNull() ?: return

        viewModelScope.launch {
            try {
                val response = repository.deleteWineById(id)
                deleteResponseState = DeleteResponse.Success(response.message)
            } catch (e: Exception) {
                deleteResponseState =
                    if (e.message !== null ) DeleteResponse.Error(e.message!!)
                    else null
            }
        }
    }

    fun resetDeleteState() {
        deleteResponseState = null
    }
}
