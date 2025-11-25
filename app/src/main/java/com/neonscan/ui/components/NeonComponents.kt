package com.neonscan.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.neonscan.ui.theme.NeonApple
import com.neonscan.ui.theme.NeonAppleDim
import com.neonscan.ui.theme.NeonBlack
import com.neonscan.ui.theme.NeonGray
import com.neonscan.ui.theme.NeonWhite

@Composable
fun NeonPrimaryButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier, enabled: Boolean = true) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = NeonApple,
            contentColor = NeonBlack,
            disabledContainerColor = NeonApple.copy(alpha = 0.4f),
            disabledContentColor = NeonBlack.copy(alpha = 0.6f)
        ),
        shape = RoundedCornerShape(18.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
    ) { Text(text) }
}

@Composable
fun NeonSecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    maxLines: Int = 1,
    selected: Boolean = false
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        border = BorderStroke(1.4.dp, if (selected) NeonApple else NeonAppleDim),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (selected) NeonAppleDim.copy(alpha = 0.25f) else Color.Transparent,
            contentColor = if (selected) NeonApple else NeonGray,
            disabledContentColor = NeonApple.copy(alpha = 0.4f)
        ),
        shape = RoundedCornerShape(18.dp)
    ) { Text(text, maxLines = maxLines, overflow = TextOverflow.Ellipsis) }
}

@Composable
fun NeonCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.4.dp, NeonApple),
        colors = CardDefaults.cardColors(
            containerColor = NeonAppleDim.copy(alpha = 0.2f),
            contentColor = NeonWhite
        )
    ) { content() }
}

@Composable
fun NeonScaffoldBackground(content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .background(NeonBlack)
    ) { content() }
}

@Composable
fun NeonFilterRow(
    items: List<Pair<String, Boolean>>,
    onItemClick: (index: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items.forEachIndexed { index, item ->
            val (label, selected) = item
            OutlinedButton(
                onClick = { onItemClick(index) },
                border = BorderStroke(1.2.dp, if (selected) NeonApple else NeonAppleDim),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (selected) NeonApple.copy(alpha = 0.15f) else Color.Transparent,
                    contentColor = if (selected) NeonApple else NeonGray
                )
            ) { Text(label) }
        }
    }
}
