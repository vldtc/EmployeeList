package com.example.employeelist.data.remote

import com.example.employeelist.data.model.people.PeopleModel
import com.example.employeelist.data.model.rooms.RoomsItemModel
import com.example.employeelist.data.model.rooms.RoomsModel
import retrofit2.http.GET

interface ApiRequest {

    @GET(ApiDetails.PEOPLE)
    suspend fun getPeople(): PeopleModel

    @GET(ApiDetails.ROOMS)
    suspend fun getRooms(): RoomsModel

}