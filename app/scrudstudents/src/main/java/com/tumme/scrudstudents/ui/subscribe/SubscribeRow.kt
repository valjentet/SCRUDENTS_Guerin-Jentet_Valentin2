package com.tumme.scrudstudents.ui.subscribe

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tumme.scrudstudents.R
import com.tumme.scrudstudents.data.local.model.SubscribeEntity
import com.tumme.scrudstudents.data.local.model.StudentEntity
import com.tumme.scrudstudents.data.local.model.CourseEntity

@Composable
fun SubscribeRow(
    subscribe: SubscribeEntity,
    students: List<StudentEntity>,
    courses: List<CourseEntity>,
    onDelete: () -> Unit
) {
    val student = students.find { it.idStudent == subscribe.studentId }
    val course = courses.find { it.idCourse == subscribe.courseId }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = student?.let { "${it.firstName} ${it.lastName}" }
                    ?: stringResource(R.string.subscribe_unknown),
                modifier = Modifier.weight(0.35f)
            )

            Text(
                text = course?.nameCourse ?: stringResource(R.string.subscribe_unknown),
                modifier = Modifier.weight(0.35f)
            )

            Text(
                text = subscribe.score.toString(),
                modifier = Modifier.weight(0.15f)
            )
            
            Row(
                modifier = Modifier.weight(0.15f)
            ) {
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = stringResource(R.string.generic_delete))
                }
            }
        }
    }
}
