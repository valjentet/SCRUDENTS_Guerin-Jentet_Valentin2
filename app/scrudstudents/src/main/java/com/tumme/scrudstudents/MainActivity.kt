package com.tumme.scrudstudents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.tumme.scrudstudents.ui.navigation.AppNavHost
import com.tumme.scrudstudents.ui.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                var currentDestination by remember { mutableStateOf(Routes.STUDENT_LIST) }

                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                selected = currentDestination == Routes.STUDENT_LIST,
                                onClick = {
                                    navController.navigate(Routes.STUDENT_LIST)
                                    currentDestination = Routes.STUDENT_LIST
                                },
                                label = { Text("Students") },
                                icon = {} // Aucun icône
                            )
                            NavigationBarItem(
                                selected = currentDestination == Routes.COURSE_LIST,
                                onClick = {
                                    navController.navigate(Routes.COURSE_LIST)
                                    currentDestination = Routes.COURSE_LIST
                                },
                                label = { Text("Courses") },
                                icon = {}
                            )
                            NavigationBarItem(
                                selected = currentDestination == Routes.SUBSCRIBE_LIST,
                                onClick = {
                                    navController.navigate(Routes.SUBSCRIBE_LIST)
                                    currentDestination = Routes.SUBSCRIBE_LIST
                                },
                                label = { Text("Subscribes") },
                                icon = {}
                            )
                        }
                    }
                ) {innerPadding ->
                    Box(Modifier.padding(innerPadding)) {
                        AppNavHost(
                            students = emptyList(),
                            courses = emptyList()
                        )
                    }
                }
            }
        }
    }
}
