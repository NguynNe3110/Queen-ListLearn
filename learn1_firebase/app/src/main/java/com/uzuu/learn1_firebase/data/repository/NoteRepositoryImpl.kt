package com.uzuu.learn1_firebase.data.repository

import com.uzuu.learn1_firebase.core.result.ApiResult
import com.uzuu.learn1_firebase.data.remote.datasource.NoteDataSource
import com.uzuu.learn1_firebase.domain.model.Note
import com.uzuu.learn1_firebase.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteSource: NoteDataSource
): NoteRepository {
    override suspend fun addNote(note: Note): ApiResult<String> {
        return noteSource.addNote(note)
    }

    override suspend fun updateNote(id: String, note: Note): ApiResult<Unit> {
        return noteSource.updateNote(id, note)
    }

    override suspend fun deleteNote(id: String): ApiResult<Unit> {
        return noteSource.deleteNote(id)
    }

    override suspend fun getNote(id: String): ApiResult<Note?> {
        return noteSource.getNote(id)
    }

    override suspend fun getAllNote(id: String): ApiResult<List<Note>> {
        return noteSource.getAllNote()
    }
}