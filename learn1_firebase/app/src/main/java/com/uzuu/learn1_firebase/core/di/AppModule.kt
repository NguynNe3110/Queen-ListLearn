package com.uzuu.learn1_firebase.core.di

import com.uzuu.learn1_firebase.data.repository.NoteRepositoryImpl
import com.uzuu.learn1_firebase.domain.repository.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    abstract fun bindNoteRepository(impl: NoteRepositoryImpl ): NoteRepository
}