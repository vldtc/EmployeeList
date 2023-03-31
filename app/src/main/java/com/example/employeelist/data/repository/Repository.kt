package com.example.employeelist.data.repository

import com.example.employeelist.data.model.people.PeopleModel
import com.example.employeelist.data.model.rooms.RoomsItemModel
import com.example.employeelist.data.model.rooms.RoomsModel

interface Repository {

    suspend fun getPeople(): PeopleModel

    suspend fun getRooms(): RoomsModel

}