package com.tumme.scrudstudents.ui.student

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tumme.scrudstudents.R
import com.tumme.scrudstudents.data.local.model.StudentEntity
import com.tumme.scrudstudents.ui.strings.localizedLabel
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun StudentRow(
    student: StudentEntity,
    onEdit: ()->Unit,
    onDelete: ()->Unit,
    onView: ()->Unit,
    onShare: ()->Unit
) {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = student.dateOfBirth.let { dateFormat.format(it) }, modifier = Modifier.weight(0.25f))
        Text(text = student.lastName, modifier = Modifier.weight(0.25f))
        Text(text = student.firstName, modifier = Modifier.weight(0.25f))
        Text(text = student.gender.localizedLabel(), modifier = Modifier.weight(0.15f))
        Row(modifier = Modifier.weight(0.10f), horizontalArrangement = Arrangement.SpaceEvenly) {
            IconButton(onClick = onView) { Icon(Icons.Default.Info, contentDescription = stringResource(R.string.generic_view)) }
            IconButton(onClick = onEdit) { Icon(Icons.Default.Edit, contentDescription = stringResource(R.string.generic_edit)) }
            IconButton(onClick = onDelete) { Icon(Icons.Default.Delete, contentDescription = stringResource(R.string.generic_delete)) }
            IconButton(onClick = onShare) { Icon(Icons.Default.Share, contentDescription = stringResource(R.string.generic_share)) }
        }
    }
    Divider()
}
