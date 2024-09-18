package com.example.cleanfirebaseauth.data.di

import com.example.cleanfirebaseauth.data.repo.AuthRepoImp
import com.example.cleanfirebaseauth.domain.repo.AuthRepo
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModulle {



    @Provides
    @Singleton
    fun provideAuth():FirebaseAuth=FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideUserRepo(auth: FirebaseAuth):AuthRepo{
        return AuthRepoImp(auth)
    }

}