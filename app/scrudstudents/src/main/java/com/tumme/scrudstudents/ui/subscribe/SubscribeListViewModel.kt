package com.tumme.scrudstudents.ui.subscribe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tumme.scrudstudents.data.local.model.SubscribeEntity
import com.tumme.scrudstudents.data.repository.SCRUDRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubscribeListViewModel @Inject constructor(
    private val repo: SCRUDRepository
) : ViewModel() {

    val subscribes = repo.getAllSubscribes().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    val students = repo.getAllStudents().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    val courses = repo.getAllCourses().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun deleteSubscribe(subscribe: SubscribeEntity) = viewModelScope.launch {
        repo.deleteSubscribe(subscribe)
    }

    fun insertSubscribe(subscribe: SubscribeEntity) = viewModelScope.launch {
        repo.insertSubscribe(subscribe)
    }
}
