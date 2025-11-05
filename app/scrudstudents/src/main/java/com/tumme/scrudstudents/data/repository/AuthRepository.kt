package com.tumme.scrudstudents.data.repository

import com.tumme.scrudstudents.data.local.dao.UserDao
import com.tumme.scrudstudents.data.local.model.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val userDao: UserDao
) {

    suspend fun getUserByEmail(email: String): UserEntity? {
        return userDao.getUserByEmail(email)
    }

    suspend fun insertUser(user: UserEntity) {
        userDao.insertUser(user)
    }
}
