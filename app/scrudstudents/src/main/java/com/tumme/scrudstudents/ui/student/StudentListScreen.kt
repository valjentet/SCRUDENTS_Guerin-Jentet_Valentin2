package com.tumme.scrudstudents.ui.student

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
fun StudentListScreen(
    viewModel: StudentListViewModel = hiltViewModel(),
    onNavigateToForm: () -> Unit = {},
    onNavigateToDetail: (Int) -> Unit = {}
) {
    val students by viewModel.students.collectAsState()
    //val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Students") })
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
            TableHeader(cells = listOf("DOB","Last", "First", "Gender", "Actions"),
                weights = listOf(0.25f, 0.25f, 0.25f, 0.15f, 0.10f))

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(students) { student ->
                    StudentRow(
                        student = student,
                        onEdit = { /* navigate to form prefilled (not implemented here) */ },
                        onDelete = { viewModel.deleteStudent(student) },
                        onView = { onNavigateToDetail(student.idStudent) },
                        onShare = { /* share intent */ }
                    )
                }
            }
        }
    }
}
