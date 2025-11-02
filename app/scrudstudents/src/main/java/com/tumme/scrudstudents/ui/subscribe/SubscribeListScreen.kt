package com.tumme.scrudstudents.ui.subscribe

import com.tumme.scrudstudents.ui.components.TableHeader
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubscribeListScreen(
    viewModel: SubscribeListViewModel = hiltViewModel(),
    onNavigateToForm: () -> Unit = {}
) {
    val subscribes by viewModel.subscribes.collectAsState()
    val students by viewModel.students.collectAsState()
    val courses by viewModel.courses.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Subscriptions") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToForm) {
                Text("+")
            }
        }
    ) { padding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(16.dp)
        ) {
            TableHeader(cells = listOf("Student", "Course", "Score", "Actions"),
                weights = listOf(0.35f, 0.35f, 0.15f, 0.15f))

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(subscribes) { subscribe ->
                    SubscribeRow(
                        subscribe = subscribe,
                        students = students,
                        courses = courses,
                        onDelete = { viewModel.deleteSubscribe(subscribe) }
                    )
                }
            }
        }
    }
}
