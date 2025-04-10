package com.example.mad_16424.data

import com.example.mad_16424.network.AllWineListResponse
import com.example.mad_16424.network.MyResponse
import com.example.mad_16424.network.WineApi
import com.example.mad_16424.network.WineRequest
import com.example.mad_16424.network.WineResponse


class WineRepository {
    suspend fun getAllWine(): AllWineListResponse{
        return WineApi.retrofitService.getAll()
    }

    suspend fun getWineById(id: Int): WineResponse {
        return WineApi.retrofitService.getOneById(id)
    }

    suspend fun createWine(wine: WineRequest): MyResponse{
        return WineApi.retrofitService.createOne(wine)
    }

    suspend fun updateWineById(wineId: Int, wineRequest: WineRequest): MyResponse{
        return WineApi.retrofitService.updateOneById(wineId, wineRequest)
    }

    suspend fun deleteWineById(id: Int): MyResponse{
        return WineApi.retrofitService.deleteOneById(id)
    }
}