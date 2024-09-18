package com.example.cleanfirebaseauth.data.repo

import com.example.cleanfirebaseauth.domain.repo.AuthRepo
import com.example.cleanfirebaseauth.util.Resource
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AuthRepoImp @Inject constructor(private val auth:FirebaseAuth):AuthRepo {

    override suspend fun register(email: String, password: String): Flow<Resource<String>> = flow{
        try {
            val result=auth.createUserWithEmailAndPassword(email,password).await()
            emit(Resource.Success(result.user?.uid?:"user null"))
        }
        catch (e:Exception){
            emit(Resource.Error("Error Message"))
        }
    }

     override fun login(email: String, password: String): Flow<Resource<String>> = flow {
       try {
           val result=auth.signInWithEmailAndPassword(email,password).await()
           emit(Resource.Success(result.user?.uid?:"user Found"))
       }
       catch (e:Exception){
           emit(Resource.Error("Error"))
       }
    }
}