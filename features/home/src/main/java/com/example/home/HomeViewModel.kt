package com.example.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.model.NewsItemUiModel
import com.example.model.base.LocalResult
import com.example.repository_api.home.HomeRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
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
       viewModelScope.launch {

           when(val response = homeRepository.todayNewsRequest(from = LocalDate.now().minusDays(1).toString().toString())) {
               is LocalResult.Success -> {
                   val items = response.data.map {
                       NewsItemUiModel(
                           id = it.id,
                           imageUrl = it.imageUrl,
                           sourceName = it.sourceName,
                           authorName = it.author,
                           title = it.title,
                           content = it.content,
                           date = it.publishedAt
                       )
                   }.take(5)
                   _state.update { it.copy(items = items) }
               }

               is LocalResult.Error -> {
                   Log.e("Error", "${response.exception}")
               }
           }

           Log.d("LOGGGGGGG", "LocalDate.now().toString().toString() ${LocalDate.now().toString().toString()}")

       }
   }

    private fun onItemClick(id: Long) {
        viewModelScope.launch {
            _effect.emit(NewsViewEffect.OpenDetails(id))
        }
    }
}
