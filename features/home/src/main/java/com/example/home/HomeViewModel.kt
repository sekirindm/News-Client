package com.example.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.model.NewsItemUiModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {

    sealed interface NewsViewEffect {
        data class OpenDetails(val id: Long) : NewsViewEffect
    }

    sealed interface NewsViewAction {
        object LoadNewsList  : NewsViewAction
        data class OnClick(val id: Long) : NewsViewAction
    }

    data class NewsViewState(
        val loading: Boolean = false,
        val items: List<NewsItemUiModel> = emptyList(),
        val error: String? = null
    )

    private val _state = MutableStateFlow(NewsViewState())
    val state: StateFlow<NewsViewState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<NewsViewEffect>()
    val effect = _effect.asSharedFlow()

    fun dispatch(action: NewsViewAction) {
        when(action) {
            is NewsViewAction.LoadNewsList -> getNewsList()
            is NewsViewAction.OnClick -> onItemClick(action.id)
        }
    }

    fun getNewsList() {

    }

    private fun onItemClick(id: Long) {
        viewModelScope.launch {
            _effect.emit(NewsViewEffect.OpenDetails(id))
        }
    }
}