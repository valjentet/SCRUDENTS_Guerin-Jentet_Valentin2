package com.tumme.scrudstudents.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tumme.scrudstudents.data.local.model.UserEntity
import com.tumme.scrudstudents.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _currentUser = MutableStateFlow<UserEntity?>(null)
    val currentUser: StateFlow<UserEntity?> = _currentUser

    private val _error = MutableStateFlow<AuthError?>(null)
    val error: StateFlow<AuthError?> = _error

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val user = repository.getUserByEmail(email)
            _error.value = when {
                user == null -> AuthError.UserNotFound
                user.password != password -> AuthError.WrongPassword
                else -> {
                    _currentUser.value = user
                    null
                }
            }
        }
    }

    fun register(name: String, email: String, password: String, role: com.tumme.scrudstudents.data.local.model.UserRole) {
        viewModelScope.launch {
            val existingUser = repository.getUserByEmail(email)
            if (existingUser != null) {
                _error.value = AuthError.EmailAlreadyRegistered
                return@launch
            }
            val newUser = UserEntity(
                name = name,
                email = email,
                password = password,
                role = role
            )
            repository.insertUser(newUser)
            _currentUser.value = newUser
            _error.value = null
        }
    }

    enum class AuthError {
        UserNotFound,
        WrongPassword,
        EmailAlreadyRegistered
    }
}
