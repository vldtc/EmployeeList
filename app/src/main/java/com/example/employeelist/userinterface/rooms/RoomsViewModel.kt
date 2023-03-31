package com.example.employeelist.userinterface.rooms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employeelist.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomsViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Whatever you want!"
    }
    val text: LiveData<String> = _text


    fun getRoomsData(){
        viewModelScope.launch {
            _text.postValue(repository.getRooms().toString())
        }
    }
}