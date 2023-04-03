package com.example.employeelist.userinterface.rooms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employeelist.data.errorhandling.ResultOf
import com.example.employeelist.data.model.people.PeopleModel
import com.example.employeelist.data.model.rooms.RoomsItemModel
import com.example.employeelist.data.model.rooms.RoomsModel
import com.example.employeelist.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class RoomsViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {
    val _rooms = MutableLiveData<ResultOf<RoomsModel>>()
    val rooms : LiveData<ResultOf<RoomsModel>> = _rooms
    fun getRoomsData() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getRooms()
            try{
                _rooms.postValue(ResultOf.Success(result))
            } catch (ioe: IOException){
                _rooms.postValue(ResultOf.Failure("[IO] error. Please retry!", ioe))
            } catch (he: HttpException){
                _rooms.postValue(ResultOf.Failure("[HTTP] error. Please try again!", he))
            }
        }
    }
}

