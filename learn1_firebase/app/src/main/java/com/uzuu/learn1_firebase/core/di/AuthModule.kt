package com.uzuu.learn1_firebase.core.di

import com.uzuu.learn1_firebase.data.repository.AuthRepositoryImpl
import com.uzuu.learn1_firebase.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {
    @Binds
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl) : AuthRepository
}