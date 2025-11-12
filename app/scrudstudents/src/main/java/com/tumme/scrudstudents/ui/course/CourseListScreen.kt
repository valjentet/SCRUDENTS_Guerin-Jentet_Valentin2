package com.tumme.scrudstudents.ui.course

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tumme.scrudstudents.R
import com.tumme.scrudstudents.ui.components.TableHeader
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
            TopAppBar(title = { Text(stringResource(R.string.courses_title)) })
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
            TableHeader(
                cells = listOf(
                    stringResource(R.string.courses_table_id),
                    stringResource(R.string.courses_table_name),
                    stringResource(R.string.courses_table_ects),
                    stringResource(R.string.courses_table_level),
                    stringResource(R.string.generic_actions)
                ),
                weights = listOf(0.15f, 0.35f, 0.15f, 0.20f, 0.15f)
            )

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
