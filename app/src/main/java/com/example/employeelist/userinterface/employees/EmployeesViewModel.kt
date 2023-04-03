package com.example.employeelist.userinterface.employees

import androidx.annotation.UiThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employeelist.data.errorhandling.ResultOf
import com.example.employeelist.data.model.people.PeopleModel
import com.example.employeelist.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class EmployeesViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {
    private val _employees = MutableLiveData< ResultOf<PeopleModel>>()
    val employees : LiveData<ResultOf<PeopleModel>> = _employees
    @UiThread
    fun loadPeopleData() {
        viewModelScope.launch(Dispatchers.IO){
            val result = repository.getPeople()
            try{
                _employees.postValue(ResultOf.Success(result))
            } catch (ioe: IOException){
                _employees.postValue(ResultOf.Failure("[IO] error. Please retry!", ioe))
            } catch (he: HttpException){
                _employees.postValue(ResultOf.Failure("[HTTP] error. Please try again!", he))
            }
        }
    }
}