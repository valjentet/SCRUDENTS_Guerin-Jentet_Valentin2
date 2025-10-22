package com.tumme.scrudstudents.ui.course

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tumme.scrudstudents.data.local.model.CourseEntity
import com.tumme.scrudstudents.data.local.model.StudentEntity
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun CourseRow(
    course: CourseEntity,
    onEdit: () -> Unit = {},
    onDelete: () -> Unit = {},
    onView: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(course.nameCourse, modifier = Modifier.weight(0.4f))
        Text(course.ectsCourse.toString(), modifier = Modifier.weight(0.2f))
        Text(course.levelCourse.value, modifier = Modifier.weight(0.2f))

        Row(modifier = Modifier.weight(0.2f), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Button(onClick = onEdit) { Text("Edit") }
            Button(onClick = onDelete) { Text("Delete") }
            Button(onClick = onView) { Text("View") }
        }
    }
}
