package com.example.cleanfirebaseauth.domain.repo

import com.example.cleanfirebaseauth.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepo {


   suspend  fun register(email:String,password:String): Flow<Resource<String>> // suspend yazmasakda olur çünkü flow zaten coroutine çalısır
   //  fun register(email:String,password:String): Flow<Resource<String>> // suspend yazmasakda olur çünkü flow zaten coroutine çalısır
  //suspend  fun register(email:String,password:String):Resource<String> // suspend yazmasakda olur çünkü flow zaten coroutine çalısır
  // bu yapıları da kullanabiliriz


   fun login(email: String,password: String):Flow<Resource<String>>

}