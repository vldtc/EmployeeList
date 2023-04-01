package com.example.employeelist.userinterface.rooms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employeelist.data.model.rooms.RoomsModel
import com.example.employeelist.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomsViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    val rooms = MutableLiveData<RoomsModel>()
    fun getRoomsData() {
        viewModelScope.launch {
            val result = repository.getRooms()
            rooms.postValue(result)
        }
    }
}