package com.tumme.scrudstudents.data.local.dao

import androidx.room.*
import com.tumme.scrudstudents.data.local.model.StudentEntity
import kotlinx.coroutines.flow.Flow

/**
 * StudentDao defines the basic operations on the students table.
 * It is an interface used by Room to automatically generate
 * the SQL code that interacts with the database. DAO inserts the class creaated
 * in Studententity.
 */
@Dao
interface StudentDao {
    @Query("SELECT * FROM students ORDER BY lastName, firstName")
    fun getAllStudents(): Flow<List<StudentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(student: StudentEntity)

    @Delete
    suspend fun delete(student: StudentEntity)

    @Query("SELECT * FROM students WHERE idStudent = :id LIMIT 1")
    suspend fun getStudentById(id: Int): StudentEntity?
}