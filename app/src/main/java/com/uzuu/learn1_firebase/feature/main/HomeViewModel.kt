package com.uzuu.learn1_firebase.feature.main

import androidx.lifecycle.ViewModel
import com.uzuu.learn1_firebase.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val auth: AuthRepository
): ViewModel() {
    fun logout(){
        auth.logout()
    }
}

