package com.tumme.scrudstudents.ui.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tumme.scrudstudents.R
import com.tumme.scrudstudents.data.local.model.UserRole
import com.tumme.scrudstudents.ui.auth.AuthViewModel.AuthError
import com.tumme.scrudstudents.ui.strings.localizedLabel

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

    val error by viewModel.error.collectAsState()
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
                label = { Text(stringResource(R.string.auth_name_label)) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(R.string.auth_email_label)) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.auth_password_label)) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        if (isRegisterMode) {
            Text(stringResource(R.string.auth_role_label), style = MaterialTheme.typography.labelLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf(UserRole.Student, UserRole.Teacher).forEach { role ->
                    Button(
                        onClick = { selectedRole = role },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedRole == role)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.surfaceVariant
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = role.localizedLabel(),
                            color = if (selectedRole == role)
                                MaterialTheme.colorScheme.onPrimary
                            else
                                MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        error?.let { authError ->
            Text(
                text = stringResource(
                    when (authError) {
                        AuthError.UserNotFound -> R.string.auth_error_user_not_found
                        AuthError.WrongPassword -> R.string.auth_error_wrong_password
                        AuthError.EmailAlreadyRegistered -> R.string.auth_error_email_registered
                    }
                ),
                color = MaterialTheme.colorScheme.error
            )
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
            Text(
                text = stringResource(
                    if (isRegisterMode) R.string.auth_register else R.string.auth_login
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            onClick = { isRegisterMode = !isRegisterMode },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(
                    if (isRegisterMode) {
                        R.string.auth_toggle_have_account
                    } else {
                        R.string.auth_toggle_no_account
                    }
                )
            )
        }
    }
}
