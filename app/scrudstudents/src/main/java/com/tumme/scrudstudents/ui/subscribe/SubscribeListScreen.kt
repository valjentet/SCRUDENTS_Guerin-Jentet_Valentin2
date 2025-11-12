package com.tumme.scrudstudents.ui.subscribe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tumme.scrudstudents.R
import com.tumme.scrudstudents.ui.components.TableHeader

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
            TopAppBar(title = { Text(stringResource(R.string.subscriptions_title)) })
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
            TableHeader(
                cells = listOf(
                    stringResource(R.string.subscriptions_table_student),
                    stringResource(R.string.subscriptions_table_course),
                    stringResource(R.string.subscriptions_table_score),
                    stringResource(R.string.generic_actions)
                ),
                weights = listOf(0.35f, 0.35f, 0.15f, 0.15f)
            )

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
