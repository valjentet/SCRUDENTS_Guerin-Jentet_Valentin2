package com.tumme.scrudstudents.ui.subscribe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tumme.scrudstudents.data.local.model.SubscribeEntity
import com.tumme.scrudstudents.data.repository.SCRUDRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for managing Subscriptions.
 * Exposes flows for the UI and handles insert/delete operations.
 */
@HiltViewModel
class SubscribeViewModel @Inject constructor(
    private val repository: SCRUDRepository
) : ViewModel() {

    // StateFlow for all subscriptions
    private val _subscribes: StateFlow<List<SubscribeEntity>> =
        repository.getAllSubscribes().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    val subscribes: StateFlow<List<SubscribeEntity>> = _subscribes

    // UI event / error messages
    private val _events = MutableSharedFlow<String>()
    val events = _events.asSharedFlow()

    // Insert a subscription
    fun insertSubscribe(subscribe: SubscribeEntity) = viewModelScope.launch {
        repository.insertSubscribe(subscribe)
        _events.emit("Subscription inserted")
    }

    // Delete a subscription
    fun deleteSubscribe(subscribe: SubscribeEntity) = viewModelScope.launch {
        repository.deleteSubscribe(subscribe)
        _events.emit("Subscription deleted")
    }

    // Helper functions to filter subscriptions by student or course
    fun getSubscribesByStudent(studentId: Int): Flow<List<SubscribeEntity>> =
        repository.getSubscribesByStudent(studentId)

    fun getSubscribesByCourse(courseId: Int): Flow<List<SubscribeEntity>> =
        repository.getSubscribesByCourse(courseId)
}
