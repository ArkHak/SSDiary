package ru.mys_ya.ssdiary.ui.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Alarm
import androidx.compose.material.icons.rounded.Task
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mys_ya.ssdiary.data.Task
import ru.mys_ya.ssdiary.ui.screens.home.LoadingScreen
import ru.mys_ya.ssdiary.util.convertTimestampToDateTime

@Composable
fun DetailTaskScreen(
    taskUiState: TaskUiState,
    modifier: Modifier = Modifier,
) {

    when (taskUiState) {
        is TaskUiState.Success -> DetailTaskScreen(
            task = taskUiState.tasks,
            modifier = modifier
        )

        is TaskUiState.Loading -> LoadingScreen()
        else -> TaskUiState.Error
    }
}

@Composable
fun DetailTaskScreen(
    task: Task,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Rounded.Task,
                contentDescription = task.name,
                modifier.size(36.dp)
            )
            Spacer(modifier = modifier.width(4.dp))
            Text(
                text = task.name,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            val currentDate = convertTimestampToDateTime(task.dateStart)
            Icon(
                imageVector = Icons.Rounded.Alarm,
                contentDescription = currentDate,
                modifier.size(28.dp)
            )
            Spacer(modifier = modifier.width(4.dp))
            Text(
                text = currentDate,
                fontSize = 16.sp
            )
        }
        Spacer(modifier = modifier.height(8.dp))
        Spacer(
            modifier = modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(color = Color.Gray)
        )
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Text(text = task.description, fontSize = 24.sp)
        }
    }
}


@Preview
@Composable
fun DetailTaskScreenPreview() {
    DetailTaskScreen(
        task = Task(
            id = 1,
            dateStart = 1682327820,
            dateFinish = 1682330400,
            name = "My task",
            description = "My task description"
        )
    )
}