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

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val user = repository.getUserByEmail(email)
            if (user == null) {
                _errorMessage.value = "User not found"
            } else if (user.password != password) {
                _errorMessage.value = "Wrong password"
            } else {
                _currentUser.value = user
                _errorMessage.value = null
            }
        }
    }

    fun register(name: String, email: String, password: String, role: com.tumme.scrudstudents.data.local.model.UserRole) {
        viewModelScope.launch {
            val existingUser = repository.getUserByEmail(email)
            if (existingUser != null) {
                _errorMessage.value = "Email already registered"
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
            _errorMessage.value = null
        }
    }
}
