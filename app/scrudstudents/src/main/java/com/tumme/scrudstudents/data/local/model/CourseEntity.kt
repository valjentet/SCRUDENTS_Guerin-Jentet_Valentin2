package com.tumme.scrudstudents.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
data class CourseEntity(
    @PrimaryKey val idCourse: Int,
    val nameCourse: String,
    val ectsCourse: Float,
    val levelCourse: LevelCourse //(values: P1, P2, P3, B1, B2, B3, A1, A2, A3, MS,PhD)
)