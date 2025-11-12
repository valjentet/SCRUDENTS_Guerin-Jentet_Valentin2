package com.tumme.scrudstudents.ui.student

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.hilt.navigation.compose.hiltViewModel
import com.tumme.scrudstudents.data.local.model.Gender
import com.tumme.scrudstudents.data.local.model.StudentEntity
import com.tumme.scrudstudents.R
import com.tumme.scrudstudents.ui.strings.localizedLabel
/**
 * Composable screen for adding a new student.
 *
 * Role:
 * Provides input fields for last name, first name, date of birth, and gender.
 * Generates a random ID for the new student.
 * Uses the StudentListViewModel to insert the student into the database.
 * Calls onSaved() callback after saving.
 *
 * Uses Jetpack Compose for layout.
 */
@Composable
fun StudentFormScreen(
    viewModel: StudentListViewModel = hiltViewModel(),
    onSaved: ()->Unit = {}
) {
    var id by remember { mutableStateOf((0..10000).random()) }
    var lastName by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var dobText by remember { mutableStateOf("2000-01-01") } // yyyy-MM-dd
    var gender by remember { mutableStateOf(Gender.NotConcerned) }

    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text(stringResource(R.string.student_form_last_name)) }
        )
        Spacer(Modifier.height(8.dp))
        TextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text(stringResource(R.string.student_form_first_name)) }
        )
        Spacer(Modifier.height(8.dp))
        TextField(
            value = dobText,
            onValueChange = { dobText = it },
            label = { Text(stringResource(R.string.student_form_birthdate)) }
        )
        Spacer(Modifier.height(8.dp))

        // Gender selector simple
        Text(stringResource(R.string.student_form_gender_label))
        Row {
            listOf(Gender.Male, Gender.Female, Gender.NotConcerned).forEach { g->
                Button(onClick = { gender = g }, modifier = Modifier.padding(end = 8.dp)) {
                    Text(g.localizedLabel())
                }
            }
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            val dob = dateFormat.parse(dobText) ?: Date()
            val student = StudentEntity(
                idStudent = id,
                lastName = lastName,
                firstName = firstName,
                dateOfBirth = dob,
                gender = gender
            )
            viewModel.insertStudent(student)
            onSaved()
        }) {
            Text(stringResource(R.string.student_form_save))
        }
    }
}
