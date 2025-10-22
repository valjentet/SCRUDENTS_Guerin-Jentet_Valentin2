package com.tumme.scrudstudents.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
/**
 * Represents the "students" table in the Room database.
 * Each instance of StudentEntity corresponds to a student record.
 * This class is therefore created each time a new student is added.
 */
@Entity(tableName = "students")
data class StudentEntity(
    @PrimaryKey val idStudent: Int, //Clé primaire pour notre bdd
    val lastName: String,
    val firstName: String,
    val dateOfBirth: Date,
    val gender: Gender
)