import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.hollyland.iq.tools.DeviceInfo
import com.hollyland.iq.tools.FilePickDialog
import com.hollyland.iq.tools.component.HighlightColor
import com.hollyland.iq.tools.component.standardBtnStyle
import com.hollyland.iq.tools.deviceInfoList
import org.jetbrains.compose.ui.tooling.preview.Preview

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "IQ Tools",
        onKeyEvent = { key ->
            println("key: $key")
            false
        }
    ) {
        appDesktop()
    }
}


@Composable
@Preview
fun appDesktop() {
    MaterialTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            var filePickState by remember { mutableStateOf(false) }
            LazyRow(
                Modifier.fillMaxWidth().wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                contentPadding = PaddingValues(16.dp)
            ) {
                items(deviceInfoList) {
                    deviceInfoItem(it) {
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
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun deviceInfoItem(deviceInfo: DeviceInfo, onClick: () -> Unit) {
    val btnStyle = standardBtnStyle()
    Row(
        modifier = Modifier
            .wrapContentSize()
            .clickable(interactionSource = btnStyle.interactionSource, indication = null) {
                println("Clicked on ${deviceInfo.name}")
                onClick()
            }.border(BorderStroke(1.dp, HighlightColor), btnStyle.shape)
            .background(color = btnStyle.backgroundColor, shape = btnStyle.shape)
            .padding(top = 13.dp, bottom = 13.dp, start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "${deviceInfo.name}: ${deviceInfo.value}",
            color = HighlightColor,
            fontFamily = FontFamily("HarmonyOS Sans SC")
        )
    }
}