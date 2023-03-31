package com.example.employeelist.data.repository

import com.example.employeelist.data.model.people.PeopleModel
import com.example.employeelist.data.model.rooms.RoomsItemModel
import com.example.employeelist.data.model.rooms.RoomsModel
import com.example.employeelist.data.remote.ApiRequest
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val apiRequest: ApiRequest
) : Repository {
    override suspend fun getPeople(): PeopleModel = apiRequest.getPeople()

    override suspend fun getRooms(): RoomsModel = apiRequest.getRooms()

}