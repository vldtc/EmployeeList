package com.example.employeelist.userinterface.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employeelist.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    private val _peopleData = MutableLiveData<Int>().apply {
    }
    val peopleData: LiveData<Int> = _peopleData

    private val _roomsData = MutableLiveData<Int>().apply {
    }
    val roomsData: LiveData<Int> = _roomsData

    private val _roomsOccupied = MutableLiveData<Int>().apply {
    }
    val roomOccupied: LiveData<Int> = _roomsOccupied

    private val _roomsVacant = MutableLiveData<Int>().apply {
    }
    val roomVacant: LiveData<Int> = _roomsVacant

    fun loadPeopleData() {
        viewModelScope.launch {
            val peopleNo = repository.getPeople().size - 1
            _peopleData.postValue(peopleNo)
        }
    }

    fun loadRoomsData() {
        viewModelScope.launch {
            val roomsNo = repository.getRooms().size
            _roomsData.postValue(roomsNo)

        }
    }

    fun loadRoomsOccupancy() {
        viewModelScope.launch {
            var occupied = 0
            var vacant = 0
            repository.getRooms().forEach {
                if (it.isOccupied == true) occupied++
                else vacant++
            }
            _roomsOccupied.postValue(occupied)
            _roomsVacant.postValue(vacant)
        }
    }
}

