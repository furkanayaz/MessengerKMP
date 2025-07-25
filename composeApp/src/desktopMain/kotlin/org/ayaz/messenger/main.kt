package org.ayaz.messenger

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Messenger",
    ) {
        App()
    }
}