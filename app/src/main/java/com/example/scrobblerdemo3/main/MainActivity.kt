package com.example.scrobblerdemo3.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.scrobblerdemo3.common.utils.UIError
import com.example.scrobblerdemo3.common.base.BaseUiState
import com.example.scrobblerdemo3.common.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import com.google.accompanist.insets.ProvideWindowInsets

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                ProvideWindowInsets {
                    val navController = rememberNavController()
                    val snackHost = remember { SnackbarHostState() }
                    Scaffold(
                        scaffoldState =
                        rememberScaffoldState(snackbarHostState = snackHost),
                    ) {
                        Content(controller = navController, host = snackHost)
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    private fun Content(
        controller: NavHostController,
        host: SnackbarHostState
    ) {
        MainRouteContent(
            navController = controller,
            errorHandler = { error ->
                when (error) {
                    is UIError.ShowErrorSnackbar ->
                        ErrorSnackbar(host = host, error = error)
                    is UIError.ScrobbleSubmissionResult ->
                        InfoSnackbar(host = host, error = error)
                }
            }
        )
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun ErrorSnackbar(host: SnackbarHostState, error: UIError.ShowErrorSnackbar) {
        val state = error.state
        if (state is BaseUiState.Error) {
            LaunchedEffect(state) {
                val result = host.showSnackbar(
                    message = state.errorMessage
                        ?: state.exception?.message
                        ?: error.fallbackMessage,
                    actionLabel = error.actionMessage
                )
                when (result) {
                    SnackbarResult.ActionPerformed -> error.onAction()
                    SnackbarResult.Dismissed -> error.onDismiss()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun InfoSnackbar(host: SnackbarHostState, error: UIError.ScrobbleSubmissionResult) {
        LaunchedEffect(error) {
            val result = host.showSnackbar(
                message = "Result: ${error.accepted} accepted, " +
                        "${error.ignored} rejected.",
                actionLabel = error.actionMessage
            )
            when (result) {
                SnackbarResult.ActionPerformed -> error.onAction()
                SnackbarResult.Dismissed -> error.onDismiss()
            }
        }
    }
}