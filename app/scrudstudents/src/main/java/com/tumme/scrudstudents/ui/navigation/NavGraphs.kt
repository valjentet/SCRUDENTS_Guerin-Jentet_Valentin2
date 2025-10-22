package com.tumme.scrudstudents.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.tumme.scrudstudents.data.local.model.CourseEntity
import com.tumme.scrudstudents.data.local.model.StudentEntity
import com.tumme.scrudstudents.ui.course.CourseDetailScreen
import com.tumme.scrudstudents.ui.course.CourseFormScreen
import com.tumme.scrudstudents.ui.course.CourseListScreen
import com.tumme.scrudstudents.ui.student.StudentListScreen
import com.tumme.scrudstudents.ui.student.StudentFormScreen
import com.tumme.scrudstudents.ui.student.StudentDetailScreen
import com.tumme.scrudstudents.ui.subscribe.SubscribeFormScreen
import com.tumme.scrudstudents.ui.subscribe.SubscribeListScreen

/**
 * AppNavHost sets up the navigation graph for the Student module.
 *
 * role:
 * Defines routes for the student list, form, and detail screens.
 * Handles navigation between screens using NavController.
 * Passes studentId as an argument to the detail screen.
 *
 * Uses Jetpack Compose Navigation and Hilt.
 */
object Routes {
    // Student routes
    const val STUDENT_LIST = "student_list"
    const val STUDENT_FORM = "student_form"
    const val STUDENT_DETAIL = "student_detail/{studentId}"

    // Course routes
    const val COURSE_LIST = "course_list"
    const val COURSE_FORM = "course_form"
    const val COURSE_DETAIL = "course_detail/{courseId}"

    // Subscribe routes
    const val SUBSCRIBE_LIST = "subscribe_list"
    const val SUBSCRIBE_FORM = "subscribe_form"
}

@Composable
fun AppNavHost(
    students: List<StudentEntity>,
    courses: List<CourseEntity>
) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Routes.STUDENT_LIST) {

        // Student screens
        composable(Routes.STUDENT_LIST) {
            StudentListScreen(
                onNavigateToForm = { navController.navigate(Routes.STUDENT_FORM) },
                onNavigateToDetail = { id -> navController.navigate("student_detail/$id") }
            )
        }
        composable(Routes.STUDENT_FORM) {
            StudentFormScreen(onSaved = { navController.popBackStack() })
        }
        composable(
            "student_detail/{studentId}",
            arguments = listOf(navArgument("studentId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("studentId") ?: 0
            StudentDetailScreen(studentId = id, onBack = { navController.popBackStack() })
        }

        // Course screens
        composable(Routes.COURSE_LIST) {
            CourseListScreen(
                onNavigateToForm = { navController.navigate(Routes.COURSE_FORM) },
                onNavigateToDetail = { id -> navController.navigate("course_detail/$id") }
            )
        }
        composable(Routes.COURSE_FORM) {
            CourseFormScreen(onSaved = { navController.popBackStack() })
        }
        composable(
            "course_detail/{courseId}",
            arguments = listOf(navArgument("courseId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("courseId") ?: 0
            CourseDetailScreen(courseId = id, onBack = { navController.popBackStack() })
        }

        // Subscribe screens
        composable(Routes.SUBSCRIBE_LIST) {
            SubscribeListScreen(
                onNavigateToForm = { navController.navigate(Routes.SUBSCRIBE_FORM) }
            )
        }
        composable(Routes.SUBSCRIBE_FORM) {
            SubscribeFormScreen(
                students = students,
                courses = courses,
                onSaved = { navController.popBackStack() }
            )
        }
    }
}