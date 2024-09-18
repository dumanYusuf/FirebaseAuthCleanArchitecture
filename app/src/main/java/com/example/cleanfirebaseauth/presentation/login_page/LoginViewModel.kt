package com.example.cleanfirebaseauth.presentation.login_page

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanfirebaseauth.domain.use_case.login_use_case.LoginUseCase
import com.example.cleanfirebaseauth.presentation.register_page.RegisterState
import com.example.cleanfirebaseauth.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val useCase: LoginUseCase):ViewModel(){

    private val _authState= MutableStateFlow<LoginState>(LoginState())
    val authState: StateFlow<LoginState> =_authState


    fun login(email: String,password:String){
        viewModelScope.launch {
            _authState.value= LoginState(loading = true)
            useCase.invoke(email,password).collect{result->
                when(result){
                    is Resource.Success->{
                        _authState.value=LoginState(isSuccess = true)
                        Log.e("succsess","viewmodel success user")
                    }
                    is Resource.Error->{
                        _authState.value=LoginState(errorMessage = "Error")
                    }
                    is Resource.Loading->{
                        _authState.value=LoginState(loading = true)
                    }
                }
            }
        }
    }

}