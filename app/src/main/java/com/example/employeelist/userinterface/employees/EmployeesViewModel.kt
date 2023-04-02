package com.example.employeelist.userinterface.employees

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employeelist.data.model.people.PeopleModel
import com.example.employeelist.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeesViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    val employees = MutableLiveData<PeopleModel>()

    fun loadPeopleData() {
        viewModelScope.launch {
            val result = repository.getPeople()
            employees.postValue(result)
        }
    }
}