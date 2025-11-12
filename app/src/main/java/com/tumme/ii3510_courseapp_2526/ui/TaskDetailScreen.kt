package com.tumme.ii3510_courseapp_2526.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tumme.ii3510_courseapp_2526.model.Task
import com.tumme.ii3510_courseapp_2526.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(task: Task?, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(task?.title ?: stringResource(R.string.no_task)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.content_description_back)
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            if (task != null) {
                Text(stringResource(R.string.task_id, task.id))
                Spacer(Modifier.height(8.dp))
                val descriptionText = if (task.description.isNotBlank()) {
                    stringResource(R.string.task_description, task.description)
                } else {
                    stringResource(R.string.task_description_empty)
                }
                Text(descriptionText)
            } else {
                Text(stringResource(R.string.task_not_found))
            }
        }
    }
}