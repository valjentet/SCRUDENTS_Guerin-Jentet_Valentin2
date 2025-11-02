package com.tumme.scrudstudents.ui.subscribe

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tumme.scrudstudents.data.local.model.SubscribeEntity
import com.tumme.scrudstudents.data.local.model.StudentEntity
import com.tumme.scrudstudents.data.local.model.CourseEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubscribeFormScreen(
    viewModel: SubscribeListViewModel = hiltViewModel(),
    onSaved: () -> Unit = {}
) {
    var selectedStudent by remember { mutableStateOf<StudentEntity?>(null) }
    var selectedCourse by remember { mutableStateOf<CourseEntity?>(null) }
    var score by remember { mutableStateOf("") }
    var expandedStudent by remember { mutableStateOf(false) }
    var expandedCourse by remember { mutableStateOf(false) }

    val students by viewModel.students.collectAsState(initial = emptyList())
    val courses by viewModel.courses.collectAsState(initial = emptyList())

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Student")

        ExposedDropdownMenuBox(
            expanded = expandedStudent,
            onExpandedChange = { expandedStudent = !expandedStudent }
        ) {
            TextField(
                value = selectedStudent?.let { "${it.firstName} ${it.lastName}" } ?: "",
                onValueChange = { /* readOnly */ },
                readOnly = true,
                label = { Text("Select Student") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedStudent) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expandedStudent,
                onDismissRequest = { expandedStudent = false }
            ) {
                students.forEach { student ->
                    DropdownMenuItem(
                        text = { Text("${student.firstName} ${student.lastName}") },
                        onClick = {
                            selectedStudent = student
                            expandedStudent = false
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Text("Course")

        ExposedDropdownMenuBox(
            expanded = expandedCourse,
            onExpandedChange = { expandedCourse = !expandedCourse }
        ) {
            TextField(
                value = selectedCourse?.nameCourse ?: "",
                onValueChange = { /* readOnly */ },
                readOnly = true,
                label = { Text("Select Course") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCourse) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expandedCourse,
                onDismissRequest = { expandedCourse = false }
            ) {
                courses.forEach { course ->
                    DropdownMenuItem(
                        text = { Text(course.nameCourse) },
                        onClick = {
                            selectedCourse = course
                            expandedCourse = false
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        TextField(
            value = score,
            onValueChange = { score = it },
            label = { Text("Score") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

        Spacer(Modifier.height(24.dp))

        val canSave = selectedStudent != null &&
                selectedCourse != null &&
                (score.toFloatOrNull()?.let { it >= 0f } == true)

        Button(
            onClick = {
                val scoreValue = score.toFloatOrNull() ?: return@Button
                val st = selectedStudent ?: return@Button
                val co = selectedCourse ?: return@Button

                val subscribe = SubscribeEntity(
                    studentId = st.idStudent,
                    courseId = co.idCourse,
                    score = scoreValue
                )
                viewModel.insertSubscribe(subscribe)
                onSaved()
            },
            enabled = canSave,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Subscription")
        }
    }
}
