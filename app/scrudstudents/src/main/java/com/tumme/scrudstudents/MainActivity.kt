package com.tumme.scrudstudents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tumme.scrudstudents.ui.navigation.AppNavHost
import com.tumme.scrudstudents.ui.navigation.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = backStackEntry?.destination?.route

                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                selected = currentRoute == Routes.STUDENT_LIST,
                                onClick = {
                                    navController.navigate(Routes.STUDENT_LIST) {
                                        popUpTo(Routes.STUDENT_LIST) { inclusive = false }
                                        launchSingleTop = true
                                    }
                                },
                                label = { Text("Students") },
                                icon = {}
                            )
                            NavigationBarItem(
                                selected = currentRoute == Routes.COURSE_LIST,
                                onClick = {
                                    navController.navigate(Routes.COURSE_LIST) {
                                        popUpTo(Routes.COURSE_LIST) { inclusive = false }
                                        launchSingleTop = true
                                    }
                                },
                                label = { Text("Courses") },
                                icon = {}
                            )
                            NavigationBarItem(
                                selected = currentRoute == Routes.SUBSCRIBE_LIST,
                                onClick = {
                                    navController.navigate(Routes.SUBSCRIBE_LIST) {
                                        popUpTo(Routes.SUBSCRIBE_LIST) { inclusive = false }
                                        launchSingleTop = true
                                    }
                                },
                                label = { Text("Subscribes") },
                                icon = {}
                            )
                        }
                    }
                ) { innerPadding ->
                    Box(Modifier.padding(innerPadding)) {
                        AppNavHost(
                            navController = navController,
                            students = emptyList(),
                            courses = emptyList()
                        )
                    }
                }
            }
        }
    }
}
