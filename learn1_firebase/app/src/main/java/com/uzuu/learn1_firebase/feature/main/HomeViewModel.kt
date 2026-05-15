package com.uzuu.learn1_firebase.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uzuu.learn1_firebase.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed class HomeUiEvent(){
    object NavigateToLogin: HomeUiEvent()
    data class Toast(val msg: String) : HomeUiEvent()
}
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val auth: AuthRepository
): ViewModel() {

    private val _uiEvent = MutableSharedFlow<HomeUiEvent>(extraBufferCapacity = 1)
    val uiEvent = _uiEvent.asSharedFlow()

    private val _channel = Channel<HomeUiEvent>()
    val channel = _channel.receiveAsFlow()


    fun logout(){
        viewModelScope.launch {
            auth.logout()
            _channel.send(HomeUiEvent.NavigateToLogin)
            _uiEvent.tryEmit(HomeUiEvent.Toast("Đăng xuất thành công"))
        }

    }
}

