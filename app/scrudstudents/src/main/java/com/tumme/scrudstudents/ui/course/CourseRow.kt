package com.tumme.scrudstudents.ui.course

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Visibility
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
import com.tumme.scrudstudents.data.local.model.CourseEntity

@Composable
fun CourseRow(
    course: CourseEntity,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onView: () -> Unit,
    onShare: () -> Unit
) {
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
                text = course.idCourse.toString(),
                modifier = Modifier.weight(0.15f)
            )

            Text(
                text = course.nameCourse,
                modifier = Modifier.weight(0.35f)
            )

            Text(
                text = course.ectsCourse.toString(),
                modifier = Modifier.weight(0.15f)
            )

            Text(
                text = course.levelCourse.value,
                modifier = Modifier.weight(0.20f)
            )

            Row(
                modifier = Modifier.weight(0.15f)
            ) {
                IconButton(onClick = onView) {
                    Icon(Icons.Default.Visibility, contentDescription = stringResource(R.string.generic_view))
                }
                IconButton(onClick = onEdit) {
                    Icon(Icons.Default.Edit, contentDescription = stringResource(R.string.generic_edit))
                }
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = stringResource(R.string.generic_delete))
                }
                IconButton(onClick = onShare) {
                    Icon(Icons.Default.Share, contentDescription = stringResource(R.string.generic_share))
                }
            }
        }
    }
}
