package com.tumme.scrudstudents.ui.course

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tumme.scrudstudents.data.local.model.CourseEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseDetailsScreen(
    courseId: Int,
    viewModel: CourseListViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit = {},
    onNavigateToEdit: () -> Unit = {}
) {
    var course by remember { mutableStateOf<CourseEntity?>(null) }

    LaunchedEffect(courseId) {
        course = viewModel.findCourse(courseId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Course Details") },
                navigationIcon = {
                    TextButton(onClick = onNavigateBack) {
                        Text("Back")
                    }
                }
            )
        }
    ) { padding ->
        if (course != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                Text("Course ID: ${course!!.idCourse}")
                Text("Course Name: ${course!!.nameCourse}")
                Text("ECTS Credits: ${course!!.ectsCourse}")
                Text("Level: ${course!!.levelCourse.value}")

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onNavigateToEdit,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Edit Course")
                }
            }
        } else {
            Text("Course not found")
        }
    }
}
