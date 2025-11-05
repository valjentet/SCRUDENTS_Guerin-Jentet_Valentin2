package com.tumme.scrudstudents.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tumme.scrudstudents.data.local.dao.CourseDao
import com.tumme.scrudstudents.data.local.dao.StudentDao
import com.tumme.scrudstudents.data.local.dao.SubscribeDao
import com.tumme.scrudstudents.data.local.dao.UserDao
import com.tumme.scrudstudents.data.local.model.*

@Database(
    entities = [UserEntity::class, StudentEntity::class, CourseEntity::class, SubscribeEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun studentDao(): StudentDao
    abstract fun courseDao(): CourseDao
    abstract fun subscribeDao(): SubscribeDao
}
