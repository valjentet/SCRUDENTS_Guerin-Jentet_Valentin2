package com.tumme.scrudstudents.ui.student

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tumme.scrudstudents.R
import com.tumme.scrudstudents.data.local.model.StudentEntity
import com.tumme.scrudstudents.ui.strings.localizedLabel
import java.text.SimpleDateFormat
import java.util.Locale
/**
 * Composable screen that displays the details of a single student.
 *
 * role:
 * Receives a studentId as a parameter.
 * Fetches the corresponding StudentEntity from the ViewModel.
 * Displays student details ID full name date of birth and gender.
 * Provides a top app bar with a back button.
 *
 * Uses Jetpack Compose Material3 components and LaunchedEffect.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentDetailScreen(
    studentId: Int,
    viewModel: StudentListViewModel = hiltViewModel(),
    onBack: ()->Unit = {}
) {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    var student by remember { mutableStateOf<StudentEntity?>(null) }

    LaunchedEffect(studentId) {
        student = viewModel.findStudent(studentId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.student_details_title)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.generic_back)
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            val studentData = student
            if (studentData == null) {
                Text(stringResource(R.string.generic_loading))
            } else {
                Text(stringResource(R.string.student_details_id, studentData.idStudent))
                Text(
                    stringResource(
                        R.string.student_details_name,
                        studentData.firstName,
                        studentData.lastName
                    )
                )
                Text(stringResource(R.string.student_details_dob, sdf.format(studentData.dateOfBirth)))
                Text(
                    stringResource(
                        R.string.student_details_gender,
                        studentData.gender.localizedLabel()
                    )
                )
            }
        }
    }
}
