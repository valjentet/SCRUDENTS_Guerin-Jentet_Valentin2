package com.tumme.scrudstudents.ui.course

import com.tumme.scrudstudents.ui.components.TableHeader
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tumme.scrudstudents.data.local.model.CourseEntity
import com.tumme.scrudstudents.ui.student.StudentListViewModel
import com.tumme.scrudstudents.ui.student.StudentRow
import kotlinx.coroutines.Job

/**
 * Composable screen that displays the list of students.
 *
 * role:
 * Observes the students StateFlow from StudentListViewModel.
 * Displays a table like list of students with columns
 * Provides a FloatingActionButton to navigate to the student form.
 * Supports editing, deleting, details, and sharing.
 *
 * Uses Material3 components and LazyColumn for efficient scrolling.
 * Column won't work
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseListScreen(
    viewModel: CourseListViewModel = hiltViewModel(),
    onNavigateToForm: () -> Unit = {},
    onNavigateToDetail: (Int) -> Unit = {}
) {
    val courses by viewModel.courses.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Courses") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToForm) { Text("+") }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            TableHeader(
                cells = listOf("Name", "ECTS", "Level", "Actions"),
                weights = listOf(0.4f, 0.2f, 0.2f, 0.2f)
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(courses) { course ->
                    CourseRow(
                        course = course,
                        //onEdit = { /* navigate to form prefilled (not implemented) */ },
                        onDelete = { viewModel.deleteCourse(course) },
                        onView = { onNavigateToDetail(course.idCourse) }
                    )
                }
            }
        }
    }
}

@Composable
fun CourseRow(course: CourseEntity, onEdit: () -> Unit, onDelete: () -> Job, onView: () -> Unit) {
    TODO("Not yet implemented")
}
