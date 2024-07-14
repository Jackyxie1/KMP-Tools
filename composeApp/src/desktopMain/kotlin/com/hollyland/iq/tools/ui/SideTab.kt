package com.hollyland.iq.tools.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hollyland.iq.tools.component.BackgroundColor
import com.hollyland.iq.tools.component.HighlightColor
import com.hollyland.iq.tools.component.TabNormalBackgroundColor


@Composable
fun SideTab(modifier: Modifier = Modifier, selectedState: State<ITab>, itemClick: (ITab) -> Unit) {
    val selectedTab by selectedState
    // 侧边栏，点击可以切换选项卡
    LazyColumn(
        modifier = Modifier.padding(4.dp).wrapContentWidth().fillMaxHeight()
            .background(color = BackgroundColor, shape = RoundedCornerShape(4.dp))
    ) {
        items(tabItemList) { item ->
            Text(
                text = item.name,
                color = if (item == selectedTab) HighlightColor else Color.White,
                modifier = modifier.padding(4.dp).background(
                    color = TabNormalBackgroundColor,
                    shape = RoundedCornerShape(4.dp)
                ).border(
                    width = 1.33.dp,
                    color = if (item == selectedTab) HighlightColor else Color.Transparent,
                    shape = RoundedCornerShape(4.dp)
                ).clickable {
                    itemClick(item)
                }.padding(8.dp)
            )
        }
    }
}


sealed interface ITab {
    val name: String
}

data object VideoPreviewTab : ITab {
    override val name: String = "视频预览"
}

// 图片编辑
data object ImageEditTab : ITab {
    override val name: String = "图片编辑"
}

data object FileTab : ITab {
    override val name: String = "文件管理"
}

data object DeviceInfoTab : ITab {
    override val name: String = "设备信息"
}

private val tabItemList by lazy {
    listOf(
        VideoPreviewTab,
        ImageEditTab,
        FileTab,
        DeviceInfoTab
    )
}