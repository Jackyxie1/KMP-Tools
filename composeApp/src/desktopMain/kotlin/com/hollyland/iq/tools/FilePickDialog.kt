package com.hollyland.iq.tools

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.awt.ComposeWindow
import java.awt.FileDialog

/**
 * @author Jacky
 *
 * @Date 2024/7/12
 **/
@Composable
fun FilePickDialog(onUpload: (String) -> Unit) {
    val (visibility, setVisibility) = remember { mutableStateOf(false) }
    return try {
        onUpload(FileDialog(ComposeWindow(), "Choose file", FileDialog.LOAD).apply {
            isAlwaysOnTop = visibility
            isVisible = true
        }.run { "${directory ?: ""}${file ?: ""}" })
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        setVisibility(false)
    }
}