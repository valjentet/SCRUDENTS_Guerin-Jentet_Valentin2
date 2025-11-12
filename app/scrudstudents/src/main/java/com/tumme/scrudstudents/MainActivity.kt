package com.tumme.scrudstudents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.tumme.scrudstudents.ui.auth.AuthScreen
import com.tumme.scrudstudents.ui.auth.AuthViewModel
import com.tumme.scrudstudents.ui.navigation.MainScreen
import com.tumme.scrudstudents.data.local.model.UserRole
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val authViewModel: AuthViewModel = hiltViewModel()
                val currentUser by authViewModel.currentUser.collectAsState()

                Scaffold { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        if (currentUser == null) {
                            AuthScreen(
                                onLoginSuccess = {},
                                onRegisterSuccess = {}
                            )
                        } else {
                            MainScreen()
                        }
                    }
                }
            }
        }
    }
}
