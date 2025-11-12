package com.tumme.scrudstudents.ui.course

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tumme.scrudstudents.R
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
            label = { Text(stringResource(R.string.course_form_name)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = ectsCourse,
            onValueChange = { ectsCourse = it },
            label = { Text(stringResource(R.string.course_form_ects)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

        Spacer(Modifier.height(16.dp))

        Text(stringResource(R.string.course_form_level))

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
            Text(stringResource(R.string.course_form_save))
        }
    }
}
