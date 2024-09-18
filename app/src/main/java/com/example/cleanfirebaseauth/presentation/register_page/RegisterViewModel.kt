package com.example.cleanfirebaseauth.presentation.register_page

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanfirebaseauth.domain.use_case.register_use_case.RegisterUseCase
import com.example.cleanfirebaseauth.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(private val useCase: RegisterUseCase):ViewModel() {


    private val _authState= MutableStateFlow<RegisterState>(RegisterState())
    val authState:StateFlow<RegisterState> =_authState


    fun register(email: String,password:String){
        viewModelScope.launch {
            _authState.value= RegisterState(loading = true)
            useCase.invoke(email,password).collect{result->
                when(result){
                    is Resource.Success->{
                        _authState.value=RegisterState(isSuccess = true)
                        Log.e("succsess","viewmodel success user")
                    }
                    is Resource.Error->{
                        _authState.value=RegisterState(errorMessage = "Error")
                    }
                    is Resource.Loading->{
                        _authState.value=RegisterState(loading = true)
                    }
                }
            }
        }
    }




}