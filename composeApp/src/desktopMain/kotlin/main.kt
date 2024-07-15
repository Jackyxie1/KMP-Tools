import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hollyland.iq.tools.ui.SideTab
import com.hollyland.iq.tools.vm.PlayerViewModel
import com.hollyland.iq.tools.vm.UiIntent
import com.hollyland.iq.tools.vm.UiViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.compose.ui.tooling.preview.Preview
import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "IQ Tools",
        state = rememberWindowState(
            size = DpSize(1920.dp, 1080.dp)
        ),
        onKeyEvent = { key ->
            println("key: $key")
            false
        }
    ) {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val discovery = NativeDiscovery().discover()
                    println("discovery: $discovery")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        AppDesktop()
    }
}


@Composable
@Preview
fun AppDesktop(
    uiViewModel: UiViewModel = viewModel { UiViewModel() },
    playerViewModel: PlayerViewModel = viewModel { PlayerViewModel() }
) {
    Box(
        modifier = Modifier.fillMaxSize()
            .padding(2.dp)
            .background(Color.Black, shape = RoundedCornerShape(4.dp))
    ) {
        SideTab(
            modifier = Modifier.fillMaxHeight(),
            selectedState = uiViewModel.selectedTab.collectAsState()
        ) {
            uiViewModel.dispatch(UiIntent.ChangeTab(it))
        }
    }
}