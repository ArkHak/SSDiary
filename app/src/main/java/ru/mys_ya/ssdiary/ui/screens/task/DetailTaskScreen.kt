package ru.mys_ya.ssdiary.ui.screens.task

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DetailTaskScreen(
    modifier: Modifier = Modifier,
    taskId: String,
) {
    Column(
        modifier = modifier
    ) {
        Text(text = taskId)
    }
}

@Preview
@Composable
fun DetailTaskScreenPreview() {
    DetailTaskScreen(taskId = "5")
}