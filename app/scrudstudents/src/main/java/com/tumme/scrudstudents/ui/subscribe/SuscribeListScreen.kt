package com.tumme.scrudstudents.ui.subscribe

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tumme.scrudstudents.data.local.model.SubscribeEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubscribeListScreen(
    viewModel: SubscribeViewModel = hiltViewModel(),
    onNavigateToForm: () -> Unit = {}
) {
    val subscribes by viewModel.subscribes.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Subscriptions") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToForm) { Text("+") }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            items(subscribes) { sub ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text("Student: ${sub.studentId}", modifier = Modifier.weight(0.3f))
                    Text("Course: ${sub.courseId}", modifier = Modifier.weight(0.3f))
                    Text("Score: ${sub.score}", modifier = Modifier.weight(0.2f))
                    Button(onClick = { viewModel.deleteSubscribe(sub) }) {
                        Text("Delete")
                    }
                }
            }
        }
    }
}
