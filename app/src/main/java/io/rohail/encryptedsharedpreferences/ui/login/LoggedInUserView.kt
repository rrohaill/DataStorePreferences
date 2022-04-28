package io.rohail.encryptedsharedpreferences.ui.login

/**
 * User details post authentication that is exposed to the UI
 */
data class LoggedInUserView(
    val displayName: String,
    val email: String,
    val password: String
    //... other data fields that may be accessible to the UI
)