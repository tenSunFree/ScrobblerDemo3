package com.example.scrobblerdemo3.common.utils

import com.example.scrobblerdemo3.common.base.BaseUiState

sealed class UIAction {
    class TagSelected(val id: String) : UIAction()
    class OpenInBrowser(val url: String) : UIAction()
    object NavigateUp : UIAction()
    object OpenNotificationListenerSettings : UIAction()
}

sealed class UIError {
    class ShowErrorSnackbar(
        val state: BaseUiState<*>?,
        val fallbackMessage: String = "Unable to load data",
        val actionMessage: String? = "Refresh",
        val onAction: () -> Unit = { },
        val onDismiss: () -> Unit = { }
    ) : UIError()

    class ScrobbleSubmissionResult(
        val accepted: Int,
        val ignored: Int,
        val actionMessage: String = "Details",
        val onAction: () -> Unit = { },
        val onDismiss: () -> Unit = { }
    ) : UIError()
}