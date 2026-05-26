package com.uzuu.learn1_firebase.data.remote.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.uzuu.learn1_firebase.core.result.ApiResult
import com.uzuu.learn1_firebase.domain.model.Note
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class NoteDataSource @Inject constructor() {
    private val db  = FirebaseFirestore.getInstance()
    val notesCollection = db.collection("Note")
    private val TAG = "[in NoteDataSource]"

//    fun addEx(note: Note) : ApiResult<Unit> {
//        db.collection("Note")
//            .add(note)
//            .addOnSuccessListener { document ->
//                if(document != null) {
//                    Log.d(TAG, "data is: ${document}")
//                } else {
//                    Log.d(TAG, "data is null")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.d(TAG, "error:", exception)
//            }
//    }

    suspend fun addNote(note: Note): ApiResult<String> {
        return try {
            val docRef =
                notesCollection
                .add(note) // đoạn này nữa phải là .add(node.toMap())
                .await()

            val newId = docRef.id
            ApiResult.Success(newId)
        } catch (e: Exception) {
            ApiResult.Error(TAG, "Failed to add note: ${e.message}")
        }
    }

    suspend fun updateNote(id: String, note: Note) : ApiResult<Unit> {
        return try {
            notesCollection
                .document(id)
                .set(note)
                .await()

            ApiResult.Success(Unit)
        } catch (e: Exception) {
            ApiResult.Error(TAG, "Failed to update note: ${e.message}")
        }
    }

    suspend fun deleteNote(id: String) : ApiResult<Unit> {
        return try {
            notesCollection
                .document(id)
                .delete()
                .await()

            ApiResult.Success(Unit)
        } catch (e: Exception) {
            ApiResult.Error(TAG, "Failed to delete note: ${e.message}")
        }
    }

    suspend fun getNote(id: String) : ApiResult<Note?> {
        return try {
            val document =
                notesCollection
                .document(id)
                .get()
                .await()

            if(document.exists()) {
                val note = document.toObject(Note::class.java)
                ApiResult.Success(note)
            } else {
                ApiResult.Success(null) // note k tồn tại
            }
        } catch (e: Exception) {
            ApiResult.Error(TAG, "Failed to get note: ${e.message}")
        }
    }

    suspend fun getAllNote() : ApiResult<List<Note>> {
        return try {
            val snapshot =
                notesCollection
                    .orderBy("createAt", Query.Direction.DESCENDING)
                    .get()
                    .await()

            val notes = snapshot.toObjects(Note::class.java)
            ApiResult.Success(notes)

        } catch (e: Exception) {
            ApiResult.Error(TAG, "Failed to get all note: ${e.message}")
        }
    }
}