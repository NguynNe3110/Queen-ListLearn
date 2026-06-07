package com.uzuu.jetpack_compose_hub.feature.listbaihoc.learn5_listAndPage.feature.rowQueen


sealed class RowQUiEvent {
    // Sự kiện thành công (ví dụ: load data xong)
    object DataLoaded : RowQUiEvent()

    // Sự kiện lỗi
    data class ShowError(val message: String) : RowQUiEvent()

    // Sự kiện điều hướng (one-time)
    data class NavigateToDetail(val itemId: Int) : RowQUiEvent()

    // Sự kiện hiển thị Snackbar/Toast
    data class ShowSnackbar(val message: String) : RowQUiEvent()
}