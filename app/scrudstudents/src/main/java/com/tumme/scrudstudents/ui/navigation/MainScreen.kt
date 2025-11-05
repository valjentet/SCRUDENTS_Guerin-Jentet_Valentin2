package com.tumme.scrudstudents.ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tumme.scrudstudents.ui.auth.AuthViewModel
import com.tumme.scrudstudents.ui.student.StudentListScreen
import com.tumme.scrudstudents.ui.student.StudentFormScreen
import com.tumme.scrudstudents.ui.student.StudentDetailScreen
import com.tumme.scrudstudents.ui.course.CourseListScreen
import com.tumme.scrudstudents.ui.course.CourseFormScreen
import com.tumme.scrudstudents.ui.course.CourseDetailsScreen
import com.tumme.scrudstudents.ui.subscribe.SubscribeListScreen
import com.tumme.scrudstudents.ui.subscribe.SubscribeFormScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val currentUser by authViewModel.currentUser.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("SCRUD Students") },
                actions = {
                    // Affichage du rôle en haut à droite
                    currentUser?.let { user ->
                        Row(
                            modifier = Modifier.padding(end = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "User role",
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = user.role.name,
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Students") },
                    label = { Text("Students") },
                    selected = currentDestination?.hierarchy?.any { it.route == "students" } == true,
                    onClick = { navController.navigate("students") }
                )

                NavigationBarItem(
                    icon = { Icon(Icons.Default.School, contentDescription = "Courses") },
                    label = { Text("Courses") },
                    selected = currentDestination?.hierarchy?.any { it.route == "courses" } == true,
                    onClick = { navController.navigate("courses") }
                )

                NavigationBarItem(
                    icon = { Icon(Icons.Default.Assignment, contentDescription = "Subscriptions") },
                    label = { Text("Subscribe") },
                    selected = currentDestination?.hierarchy?.any { it.route == "subscribes" } == true,
                    onClick = { navController.navigate("subscribes") }
                )
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "students",
            modifier = Modifier.padding(padding)
        ) {
            composable("students") {
                StudentListScreen(
                    onNavigateToForm = { navController.navigate("student_form") },
                    onNavigateToDetail = { id -> navController.navigate("student_detail/$id") }
                )
            }
            composable("student_form") {
                StudentFormScreen(onSaved = { navController.popBackStack() })
            }
            composable("student_detail/{studentId}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("studentId")?.toIntOrNull() ?: 0
                StudentDetailScreen(studentId = id, onBack = { navController.popBackStack() })
            }

            composable("courses") {
                CourseListScreen(
                    onNavigateToForm = { navController.navigate("course_form") },
                    onNavigateToDetail = { id -> navController.navigate("course_detail/$id") }
                )
            }
            composable("course_form") {
                CourseFormScreen(onSaved = { navController.popBackStack() })
            }
            composable("course_detail/{courseId}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("courseId")?.toIntOrNull() ?: 0
                CourseDetailsScreen(
                    courseId = id,
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateToEdit = { }
                )
            }

            composable("subscribes") {
                SubscribeListScreen(
                    onNavigateToForm = { navController.navigate("subscribe_form") }
                )
            }
            composable("subscribe_form") {
                SubscribeFormScreen(onSaved = { navController.popBackStack() })
            }
        }
    }
}