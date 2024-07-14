package com.hollyland.iq.tools.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.hollyland.iq.tools.DeviceInfo
import com.hollyland.iq.tools.FilePickDialog
import com.hollyland.iq.tools.component.HighlightColor
import com.hollyland.iq.tools.component.TextColor
import com.hollyland.iq.tools.component.standardBtnStyle
import com.hollyland.iq.tools.deviceInfoList

@Composable
fun TestPage(modifier: Modifier = Modifier) {
    var filePickState by remember { mutableStateOf(false) }
    LazyColumn(
        Modifier.wrapContentSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        contentPadding = PaddingValues(16.dp)
    ) {
        items(deviceInfoList) {
            DeviceInfoItem(it) {
                filePickState = !filePickState
            }
        }
    }

    if (filePickState) {
        FilePickDialog {
            println("file path: $it")
        }
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun DeviceInfoItem(deviceInfo: DeviceInfo, onClick: () -> Unit) {
    val btnStyle = standardBtnStyle()
    Row(
        modifier = Modifier
            .wrapContentSize()
            .clickable(interactionSource = btnStyle.interactionSource, indication = null) {
                println("Clicked on ${deviceInfo.name}")
                onClick()
            }
            .padding(4.dp)
    ) {
        Text(
            modifier = Modifier
                .border(BorderStroke(1.dp, HighlightColor), btnStyle.shape)
                .background(color = btnStyle.backgroundColor, shape = btnStyle.shape)
                .padding(16.dp),
            text = "${deviceInfo.name}: ${deviceInfo.value}",
            color = TextColor,
            fontFamily = FontFamily("HarmonyOS Sans SC")
        )
    }
}