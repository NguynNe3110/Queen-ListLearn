package com.uzuu.learn1_firebase.domain.repository

import com.uzuu.learn1_firebase.core.result.ApiResult
import com.uzuu.learn1_firebase.domain.model.Note

interface NoteRepository {
    suspend fun addNote(note: Note) : ApiResult<String>
    suspend fun updateNote(id: String, note: Note) : ApiResult<Unit>
    suspend fun deleteNote(id: String) : ApiResult<Unit>
    suspend fun getNote(id: String) : ApiResult<Note?>
    suspend fun getAllNote(id: String) : ApiResult<List<Note>>
}