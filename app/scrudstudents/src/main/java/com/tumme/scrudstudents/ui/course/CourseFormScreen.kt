package com.tumme.scrudstudents.ui.course

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tumme.scrudstudents.data.local.model.CourseEntity
import com.tumme.scrudstudents.data.local.model.LevelCourse
import kotlin.random.Random

@Composable
fun CourseFormScreen(
    viewModel: CourseListViewModel = hiltViewModel(),
    onSaved: () -> Unit = {}
) {
    var id by remember { mutableStateOf(Random.nextInt(0, 10000)) }
    var name by remember { mutableStateOf("") }
    var ectsText by remember { mutableStateOf("") }
    var level by remember { mutableStateOf(LevelCourse.P1) }
    var errorMessage by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Nom du cours
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Course Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        // ECTS
        TextField(
            value = ectsText,
            onValueChange = { ectsText = it },
            label = { Text("ECTS") },
            placeholder = { Text("ex: 5.0") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        // Sélecteur de niveau
        Text("Select Level:")
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            LevelCourse.values().forEach { l ->
                Button(
                    onClick = { level = l },
                    colors = if (level == l) ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
                    else ButtonDefaults.buttonColors()
                ) {
                    Text(l.name) // utiliser .name si enum n'a pas de 'value'
                }
            }
        }
        Spacer(Modifier.height(8.dp))

        // Message d'erreur
        if (errorMessage.isNotEmpty()) {
            Text(errorMessage, color = MaterialTheme.colorScheme.error)
            Spacer(Modifier.height(8.dp))
        }

        // Bouton Save
        Button(
            onClick = {
                val ects = ectsText.toFloatOrNull()
                if (name.isBlank()) {
                    errorMessage = "Course name is required"
                    return@Button
                }
                if (ects == null || ects <= 0f) {
                    errorMessage = "ECTS must be greater than 0"
                    return@Button
                }

                val course = CourseEntity(
                    idCourse = id,
                    nameCourse = name,
                    ectsCourse = ects,
                    levelCourse = level
                )

                viewModel.insertCourse(course)
                onSaved()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }
    }
}
