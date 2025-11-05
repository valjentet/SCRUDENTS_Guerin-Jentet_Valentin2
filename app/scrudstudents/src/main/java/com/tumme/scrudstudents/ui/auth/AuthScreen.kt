package com.tumme.scrudstudents.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tumme.scrudstudents.data.local.model.UserRole

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit = {},
    onRegisterSuccess: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var isRegisterMode by remember { mutableStateOf(false) }
    var selectedRole by remember { mutableStateOf(UserRole.Student) }

    val errorMessage by viewModel.errorMessage.collectAsState()
    val currentUser by viewModel.currentUser.collectAsState()

    // Callback dès que currentUser est non-null
    LaunchedEffect(currentUser) {
        if (currentUser != null) {
            if (isRegisterMode) onRegisterSuccess() else onLoginSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        if (isRegisterMode) {
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        if (isRegisterMode) {
            Text("Role:")
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf(UserRole.Student, UserRole.Teacher).forEach { role ->
                    Button(
                        onClick = { selectedRole = role },
                        colors = if (selectedRole == role)
                            ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
                        else
                            ButtonDefaults.buttonColors()
                    ) {
                        Text(role.name)
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        if (errorMessage != null) {
            Text(errorMessage!!, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = {
                if (isRegisterMode) {
                    viewModel.register(name, email, password, selectedRole)
                } else {
                    viewModel.login(email, password)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isRegisterMode) "Register" else "Login")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            onClick = { isRegisterMode = !isRegisterMode },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isRegisterMode) "Already have an account? Login" else "Don't have an account? Register")
        }
    }
}
