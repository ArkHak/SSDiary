package ru.mys_ya.ssdiary.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.boguszpawlowski.composecalendar.SelectableWeekCalendar
import io.github.boguszpawlowski.composecalendar.WeekCalendarState
import io.github.boguszpawlowski.composecalendar.rememberSelectableWeekCalendarState
import io.github.boguszpawlowski.composecalendar.selection.DynamicSelectionState
import org.koin.androidx.compose.koinViewModel
import ru.mys_ya.ssdiary.R
import ru.mys_ya.ssdiary.data.Task

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onSelectTask: (String) -> Unit,
    viewModel: SSDiaryViewModel = koinViewModel(),
) {
    val calendarState = rememberSelectableWeekCalendarState()
    val homeUiState by viewModel.homeUiState.collectAsState()

    Column(
        modifier = modifier.padding(dimensionResource(id = R.dimen.default_padding))
    ) {
        Calendar(calendarState = calendarState)
        TaskList(
            tasks = homeUiState.taskList,
            onSelectTask = {
                onSelectTask(it)
            })
    }
}

@Composable
fun Calendar(
    modifier: Modifier = Modifier,
    calendarState: WeekCalendarState<DynamicSelectionState>,
) {
    SelectableWeekCalendar(calendarState = calendarState)
}

@Composable
private fun TaskList(
    modifier: Modifier = Modifier,
    tasks: List<Task>,
    onSelectTask: (String) -> Unit,
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(
            items = tasks,
            key = { task ->
                task.name
            }
        ) { task ->
            ItemTask(
                task = task,
                onClickItem = { onSelectTask(it) })
        }
    }
}

@Composable
fun ItemTask(
    task: Task,
    modifier: Modifier = Modifier,
    onClickItem: (String) -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClickItem(task.name)
            },
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = modifier.padding(dimensionResource(id = R.dimen.default_padding)),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(text = task.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = modifier.height(2.dp))
            Text(text = task.dateStart.toString(), fontSize = 14.sp)
        }
    }
}