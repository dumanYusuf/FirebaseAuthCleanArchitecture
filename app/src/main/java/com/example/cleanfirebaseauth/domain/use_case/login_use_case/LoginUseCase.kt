package com.example.cleanfirebaseauth.domain.use_case.login_use_case

import com.example.cleanfirebaseauth.domain.repo.AuthRepo
import com.example.cleanfirebaseauth.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repo: AuthRepo) {


      fun invoke(email:String,password:String): Flow<Resource<String>> {
        return repo.login(email,password)
    }

}