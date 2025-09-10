package com.example.walmartcodingassessment.ui.view.uistate

import com.example.walmartcodingassessment.domain.model.Country

sealed class UIState {
    object Loading : UIState()
    data class Success(val data: List<Country>) : UIState()
    data class Error(val message: String) : UIState()
}