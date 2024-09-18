package com.example.cleanfirebaseauth.domain.use_case.register_use_case

import com.example.cleanfirebaseauth.domain.repo.AuthRepo
import com.example.cleanfirebaseauth.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RegisterUseCase @Inject constructor(private val repo:AuthRepo){

    suspend operator fun invoke(email:String,password:String): Flow<Resource<String>> {
        return repo.register(email,password)
    }

}