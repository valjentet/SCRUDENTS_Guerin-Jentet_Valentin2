package com.tumme.scrudstudents.di

import android.content.Context
import androidx.room.Room
import com.tumme.scrudstudents.data.local.AppDatabase
import com.tumme.scrudstudents.data.local.dao.*
import com.tumme.scrudstudents.data.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "scrud-db").build()

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

    @Provides
    fun provideStudentDao(db: AppDatabase): StudentDao = db.studentDao()

    @Provides
    fun provideCourseDao(db: AppDatabase): CourseDao = db.courseDao()

    @Provides
    fun provideSubscribeDao(db: AppDatabase): SubscribeDao = db.subscribeDao()

    @Provides
    @Singleton
    fun provideAuthRepository(userDao: UserDao): AuthRepository =
        AuthRepository(userDao)

    @Provides
    @Singleton
    fun provideSCRUDRepository(studentDao: StudentDao, courseDao: CourseDao,
                               subscribeDao: SubscribeDao): SCRUDRepository =
        SCRUDRepository(studentDao, courseDao, subscribeDao)
}
