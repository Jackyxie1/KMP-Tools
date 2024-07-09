package com.hollyland.iq.tools.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/**
 * @author Jacky
 *
 * @Date 2024/7/12
 **/

/**
 * 标准的按钮样式，确认了按钮的正常状态以及按下状态
 */
@Composable
fun standardBtnStyle(): BtnStyle {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val backgroundColor = if (isPressed) PressBtnColor else NormalBtnColor
    return BtnStyle(interactionSource, backgroundColor)
}

data class BtnStyle(
    val interactionSource: MutableInteractionSource,
    val backgroundColor: Color,
    val shape: Shape = RoundedCornerShape(7.dp)
)