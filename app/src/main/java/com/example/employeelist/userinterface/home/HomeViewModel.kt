package com.example.employeelist.userinterface.home

import androidx.annotation.UiThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employeelist.data.errorhandling.ResultOf
import com.example.employeelist.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

private fun <T> MutableLiveData<T>.postValue(success: ResultOf.Success<T>) {

}

@HiltViewModel
class HomeViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    private val _peopleData = MutableLiveData<ResultOf<Int>>()
    val peopleData: LiveData<ResultOf<Int>> = _peopleData

    private val _roomsData = MutableLiveData<ResultOf<Int>>().apply {
    }
    val roomsData: LiveData<ResultOf<Int>> = _roomsData

    private val _roomsOccupied = MutableLiveData<ResultOf<Int>>().apply {
    }
    val roomOccupied: LiveData<ResultOf<Int>> = _roomsOccupied

    private val _roomsVacant = MutableLiveData<ResultOf<Int>>().apply {
    }
    val roomVacant: LiveData<ResultOf<Int>> = _roomsVacant

    @UiThread
    fun loadPeopleData() {
        viewModelScope.launch(Dispatchers.IO) {
            val peopleNo = repository.getPeople().size - 1
            try {
                _peopleData.postValue(ResultOf.Success(peopleNo))
            } catch (ioe: IOException) {
                _peopleData.postValue(ResultOf.Failure("[IO] error. Please retry!", ioe))
            } catch (he: HttpException) {
                _peopleData.postValue(ResultOf.Failure("[HTTP] error. Please retry!", he))
            }
        }
    }

    fun loadRoomsData() {
        viewModelScope.launch(Dispatchers.IO) {
            var occupied = 0
            var vacant = 0
            val roomsNo = repository.getRooms().size
            try {
                repository.getRooms().forEach {
                    if (it.isOccupied == true) occupied++
                    else vacant++
                }
                _roomsOccupied.postValue(ResultOf.Success(occupied))
                _roomsVacant.postValue(ResultOf.Success(vacant))
                _roomsData.postValue(ResultOf.Success(roomsNo))
            } catch (ioe: IOException) {
                _roomsOccupied.postValue(ResultOf.Failure("[IO] error. Please retry!", ioe))
                _roomsVacant.postValue(ResultOf.Failure("[IO] error. Please retry!", ioe))
                _roomsData.postValue(ResultOf.Failure("[IO] error. Please retry!", ioe))
            } catch (he: HttpException) {
                _roomsOccupied.postValue(ResultOf.Failure("[HTTP] error. Please retry!", he))
                _roomsVacant.postValue(ResultOf.Failure("[HTTP] error. Please retry!", he))
                _roomsData.postValue(ResultOf.Failure("[HTTP] error. Please retry!", he))
            }


        }
    }

}

