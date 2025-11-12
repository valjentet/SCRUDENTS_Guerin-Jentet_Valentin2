package com.tumme.scrudstudents.ui.course

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
                title = { Text(stringResource(R.string.course_details_title)) },
                navigationIcon = {
                    TextButton(onClick = onNavigateBack) {
                        Text(stringResource(R.string.generic_back))
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
                Text(stringResource(R.string.course_details_id, course!!.idCourse))
                Text(stringResource(R.string.course_details_name, course!!.nameCourse))
                Text(stringResource(R.string.course_details_ects, course!!.ectsCourse))
                Text(stringResource(R.string.course_details_level, course!!.levelCourse.value))

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onNavigateToEdit,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.course_details_edit))
                }
            }
        } else {
            Text(stringResource(R.string.course_details_not_found))
        }
    }
}
