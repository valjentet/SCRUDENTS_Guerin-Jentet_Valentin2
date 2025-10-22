package com.tumme.scrudstudents.ui.subscribe

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tumme.scrudstudents.data.local.model.SubscribeEntity
import com.tumme.scrudstudents.data.local.model.StudentEntity
import com.tumme.scrudstudents.data.local.model.CourseEntity
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubscribeFormScreen(
    viewModel: SubscribeViewModel = hiltViewModel(),
    students: List<StudentEntity>,
    courses: List<CourseEntity>,
    onSaved: () -> Unit = {}
) {
    var selectedStudent by remember { mutableStateOf<StudentEntity?>(null) }
    var selectedCourse by remember { mutableStateOf<CourseEntity?>(null) }
    var scoreText by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        // Student dropdown
        ExposedDropdownMenuBox(
            expanded = selectedStudent == null,
            onExpandedChange = { }
        ) {
            TextField(
                value = selectedStudent?.lastName ?: "Select Student",
                onValueChange = {},
                readOnly = true,
                label = { Text("Student") }
            )
            DropdownMenu(expanded = true, onDismissRequest = { }) {
                students.forEach { student ->
                    DropdownMenuItem(text = { Text("${student.firstName} ${student.lastName}") },
                        onClick = { selectedStudent = student })
                }
            }
        }

        Spacer(Modifier.height(8.dp))

        // Course dropdown
        ExposedDropdownMenuBox(
            expanded = selectedCourse == null,
            onExpandedChange = { }
        ) {
            TextField(
                value = selectedCourse?.nameCourse ?: "Select Course",
                onValueChange = {},
                readOnly = true,
                label = { Text("Course") }
            )
            DropdownMenu(expanded = true, onDismissRequest = { }) {
                courses.forEach { course ->
                    DropdownMenuItem(text = { Text(course.nameCourse) },
                        onClick = { selectedCourse = course })
                }
            }
        }

        Spacer(Modifier.height(8.dp))

        // Score input
        TextField(
            value = scoreText,
            onValueChange = { scoreText = it },
            label = { Text("Score") }
        )

        if (errorMessage.isNotEmpty()) {
            Text(errorMessage, color = MaterialTheme.colorScheme.error)
            Spacer(Modifier.height(8.dp))
        }

        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            val score = scoreText.toFloatOrNull()
            if (selectedStudent == null || selectedCourse == null) {
                errorMessage = "Select student and course"
                return@Button
            }
            if (score == null || score < 0f) {
                errorMessage = "Score must be >= 0"
                return@Button
            }

            val subscribe = SubscribeEntity(
                studentId = selectedStudent!!.idStudent,
                courseId = selectedCourse!!.idCourse,
                score = score
            )
            viewModel.insertSubscribe(subscribe)
            onSaved()
        }) {
            Text("Save")
        }
    }
}
