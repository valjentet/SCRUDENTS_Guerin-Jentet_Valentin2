package com.tumme.scrudstudents.ui.course

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tumme.scrudstudents.data.local.model.CourseEntity
import com.tumme.scrudstudents.data.local.model.LevelCourse

@Composable
fun CourseFormScreen(
    viewModel: CourseListViewModel = hiltViewModel(),
    onSaved: () -> Unit = {}
) {
    var id by remember { mutableStateOf((0..10000).random()) }
    var nameCourse by remember { mutableStateOf("") }
    var ectsCourse by remember { mutableStateOf("") }
    var levelCourse by remember { mutableStateOf(LevelCourse.P1) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TextField(
            value = nameCourse,
            onValueChange = { nameCourse = it },
            label = { Text("Course Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = ectsCourse,
            onValueChange = { ectsCourse = it },
            label = { Text("ECTS Credits") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

        Spacer(Modifier.height(16.dp))

        Text("Course Level")

        Row {
            LevelCourse.entries.forEach { level ->
                Button(
                    onClick = { levelCourse = level },
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text(level.value)
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                val ectsValue = ectsCourse.toFloatOrNull() ?: 0f
                if (ectsValue > 0) {
                    val course = CourseEntity(
                        idCourse = id,
                        nameCourse = nameCourse,
                        ectsCourse = ectsValue,
                        levelCourse = levelCourse
                    )
                    viewModel.insertCourse(course)
                    onSaved()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Course")
        }
    }
}
