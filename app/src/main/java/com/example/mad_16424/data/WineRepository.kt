package com.example.mad_16424.data

import com.example.mad_16424.model.Wine
import com.example.mad_16424.network.AllWineListResponse
import com.example.mad_16424.network.MyResponse
import com.example.mad_16424.network.WineApi
import com.example.mad_16424.network.WineApiServices
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

    suspend fun updateWineById(wine: Wine): MyResponse{
        val wineRequest = WineRequest()
        wineRequest.wineName = wine.wineName
        wineRequest.wineAge = wine.wineAge
        wineRequest.date = wine.date
        wineRequest.rating = wine.rating
        wineRequest.price = wine.price
        wineRequest.alcoholVolume = wine.alcoholVolume
        wineRequest.bottleVolume = wine.bottleVolume
        wineRequest.description = wine.description
        wineRequest.drinkTime = wine.drinkTime
        wineRequest.frenchOak = wine.frenchOak
        wineRequest.quantity = wine.quantity
        return WineApi.retrofitService.updateOneById(wine.id, wineRequest)
    }

    suspend fun deleteWineById(id: Int): MyResponse{
        return WineApi.retrofitService.deleteOneById(id)
    }
}