package com.tumme.scrudstudents.ui.student

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tumme.scrudstudents.data.local.model.StudentEntity
import com.tumme.scrudstudents.data.repository.SCRUDRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the Student list screen.
 *
 * Responsibilities:
 * Exposes a StateFlow of students for the UI to observe.
 * Handles CRUD operations by calling the SCRUDRepository.
 * its UI events via a SharedFlow.
 * Uses Hilt for dependency injection and coroutines for asynchronous operations.
 */

@HiltViewModel
class StudentListViewModel @Inject constructor(
    private val repo: SCRUDRepository
) : ViewModel() {

    private val _students: StateFlow<List<StudentEntity>> =
        repo.getAllStudents().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    val students: StateFlow<List<StudentEntity>> = _students

    // UI event / error Flow
    private val _events = MutableSharedFlow<StudentListEvent>()
    val events = _events.asSharedFlow()

    fun deleteStudent(student: StudentEntity) = viewModelScope.launch {
        repo.deleteStudent(student)
        _events.emit(StudentListEvent.StudentDeleted)
    }

    fun insertStudent(student: StudentEntity) = viewModelScope.launch {
        repo.insertStudent(student)
        _events.emit(StudentListEvent.StudentInserted)
    }

    suspend fun findStudent(id: Int) = repo.getStudentById(id)

    sealed class StudentListEvent {
        data object StudentDeleted : StudentListEvent()
        data object StudentInserted : StudentListEvent()
    }
}