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
        topBar = {
            TopAppBar(title = { Text("Courses") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToForm) {
                Text("+")
            }
        }
    ) { padding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(16.dp)
        ) {
            TableHeader(cells = listOf("ID", "Name", "ECTS", "Level", "Actions"),
                weights = listOf(0.15f, 0.35f, 0.15f, 0.20f, 0.15f))

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(courses) { course ->
                    CourseRow(
                        course = course,
                        onEdit = { },
                        onDelete = { viewModel.deleteCourse(course) },
                        onView = { onNavigateToDetail(course.idCourse) },
                        onShare = { }
                    )
                }
            }
        }
    }
}
