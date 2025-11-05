package com.tumme.scrudstudents.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.compose.rememberNavController
import com.tumme.scrudstudents.data.local.model.CourseEntity
import com.tumme.scrudstudents.data.local.model.StudentEntity
import com.tumme.scrudstudents.data.local.model.UserRole
import com.tumme.scrudstudents.ui.course.CourseDetailsScreen
import com.tumme.scrudstudents.ui.course.CourseFormScreen
import com.tumme.scrudstudents.ui.course.CourseListScreen
import com.tumme.scrudstudents.ui.student.StudentDetailScreen
import com.tumme.scrudstudents.ui.student.StudentFormScreen
import com.tumme.scrudstudents.ui.student.StudentListScreen
import com.tumme.scrudstudents.ui.subscribe.SubscribeFormScreen
import com.tumme.scrudstudents.ui.subscribe.SubscribeListScreen

object Routes {
    const val STUDENT_LIST = "student_list"
    const val STUDENT_FORM = "student_form"
    const val STUDENT_DETAIL = "student_detail/{studentId}"

    const val COURSE_LIST = "course_list"
    const val COURSE_FORM = "course_form"
    const val COURSE_DETAIL = "course_detail/{courseId}"

    const val SUBSCRIBE_LIST = "subscribe_list"
    const val SUBSCRIBE_FORM = "subscribe_form"

    const val TEACHER_LIST = "teacher_list"
}

@Composable
fun AppNavHost(
    role: UserRole,
    students: List<StudentEntity> = emptyList(),
    courses: List<CourseEntity> = emptyList()
) {
    val navController = rememberNavController()

    when (role) {
        UserRole.Student -> {
            NavHost(navController, startDestination = Routes.STUDENT_LIST) {

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
                    CourseDetailsScreen(courseId = id, onNavigateBack = { navController.popBackStack() }, onNavigateToEdit = { })
                }

                composable(Routes.SUBSCRIBE_LIST) {
                    SubscribeListScreen(onNavigateToForm = { navController.navigate(Routes.SUBSCRIBE_FORM) })
                }

                composable(Routes.SUBSCRIBE_FORM) {
                    SubscribeFormScreen(onSaved = { navController.popBackStack() })
                }
            }
        }

        UserRole.Teacher -> {
            NavHost(navController, startDestination = Routes.TEACHER_LIST) {
                composable(Routes.TEACHER_LIST) {
                    // Ici tu peux ajouter un écran Dashboard enseignant
                    // TeacherDashboardScreen(navController, students, courses)
                }

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
                    CourseDetailsScreen(courseId = id, onNavigateBack = { navController.popBackStack() }, onNavigateToEdit = { })
                }
            }
        }
    }
}
