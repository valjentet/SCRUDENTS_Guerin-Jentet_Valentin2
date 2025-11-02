package com.tumme.scrudstudents.ui.course

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tumme.scrudstudents.data.local.model.CourseEntity
import com.tumme.scrudstudents.data.repository.SCRUDRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseListViewModel @Inject constructor(
    private val repo: SCRUDRepository
) : ViewModel() {

    val courses = repo.getAllCourses().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun deleteCourse(course: CourseEntity) = viewModelScope.launch {
        repo.deleteCourse(course)
    }

    fun insertCourse(course: CourseEntity) = viewModelScope.launch {
        repo.insertCourse(course)
    }

    suspend fun findCourse(id: Int) = repo.getCourseById(id)
}
