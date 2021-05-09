package com.example.scrobblerdemo3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomDividerComponent(
    modifier: Modifier = Modifier,
    color: Color = Color.Gray,
    thickness: Dp = 1.dp,
    startIndent: Dp = 0.dp,
    vertical: Boolean = false
) {
    val indentMod = if (startIndent.value != 0f) {
        if (vertical) {
            Modifier.padding(vertical = startIndent)
        } else {
            Modifier.padding(start = startIndent)
        }
    } else {
        Modifier
    }
    if (vertical) {
        Box(
            modifier
                .then(indentMod)
                .fillMaxHeight()
                .width(thickness)
                .background(color)
        )
    } else {
        Box(
            modifier
                .then(indentMod)
                .fillMaxWidth()
                .height(thickness)
                .background(color)
        )
    }
}