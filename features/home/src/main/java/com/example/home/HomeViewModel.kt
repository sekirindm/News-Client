package com.example.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.home.model.NewsItemUiModel
import com.example.repository_api.home.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate


class HomeViewModel(application: Application, private val homeRepository: HomeRepository) : AndroidViewModel(application) {
    sealed interface NewsViewEffect {
        data class OpenDetails(val id: Long) : NewsViewEffect
    }

    sealed interface NewsViewAction {
        object LoadNewsList  : NewsViewAction
        data class OnClick(val id: Long) : NewsViewAction
    }

    data class NewsViewState(
        val loading: Boolean = false,
        val items: Flow<PagingData<NewsItemUiModel>>? = null,
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
       viewModelScope.launch {
           val pagingFlow = homeRepository.todayNewsRequest(LocalDate.now().minusDays(1).toString())
                   .map { pagingData ->
                       pagingData.map { entity ->
                           NewsItemUiModel(
                               id = entity.id,
                               imageUrl = entity.imageUrl,
                               sourceName = entity.sourceName,
                               authorName = entity.author,
                               title = entity.title,
                               content = entity.content,
                               date = entity.publishedAt
                           )
                       }
                   }.cachedIn(viewModelScope)

           _state.update { it.copy(items = pagingFlow) }
       }
   }

    private fun onItemClick(id: Long) {
        viewModelScope.launch {
            _effect.emit(NewsViewEffect.OpenDetails(id))
        }
    }
}
