package com.example.walmartcodingassessment.ui.view.uistate

sealed class UIState {
    object Loading : UIState()
    data class Success(val data: Any) : UIState()
    data class Error(val message: String) : UIState()
}