package ru.mys_ya.ssdiary.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Alarm
import androidx.compose.material.icons.rounded.Task
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.boguszpawlowski.composecalendar.SelectableWeekCalendar
import io.github.boguszpawlowski.composecalendar.WeekCalendarState
import io.github.boguszpawlowski.composecalendar.rememberSelectableWeekCalendarState
import io.github.boguszpawlowski.composecalendar.selection.DynamicSelectionState
import ru.mys_ya.ssdiary.R
import ru.mys_ya.ssdiary.data.Task
import ru.mys_ya.ssdiary.util.convertDateToTimestamp
import ru.mys_ya.ssdiary.util.convertTimestampToTime
import java.time.LocalDate

@Composable
fun HomeScreen(
    homeUiState: HomeUiState,
    onSelectTask: (String) -> Unit,
    onSelectDate: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    val calendarState = rememberSelectableWeekCalendarState()

    Column(modifier = modifier) {
        Calendar(
            calendarState = calendarState,
            modifier = modifier,
            onSelectDate = {
                onSelectDate(it)
            }
        )
        when (homeUiState) {
            is HomeUiState.Success -> TaskListScreen(
                tasks = homeUiState.tasks,
                modifier = modifier,
                onSelectTask = {
                    onSelectTask(it)
                }
            )
            is HomeUiState.Loading -> LoadingScreen()
            else -> HomeUiState.Error
        }
    }
}

@Composable
fun Calendar(
    calendarState: WeekCalendarState<DynamicSelectionState>,
    modifier: Modifier = Modifier,
    onSelectDate: (Long) -> Unit,
) {
    SelectableWeekCalendar(calendarState = calendarState)
    val date = calendarState.selectionState.selection.joinToString { it.toString() }
    onSelectDate(
        if (date.isBlank()) {
            convertDateToTimestamp(LocalDate.now().toString())
        } else {
            convertDateToTimestamp(date)
        }
    )
}

@Composable
private fun TaskListScreen(
    tasks: List<Task>,
    modifier: Modifier = Modifier,
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
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.Task,
                    contentDescription = task.name
                )
                Spacer(modifier = modifier.width(4.dp))
                Text(text = task.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = modifier.height(2.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                val currentDate = convertTimestampToTime(task.dateStart)
                Icon(
                    imageVector = Icons.Rounded.Alarm,
                    contentDescription = currentDate
                )
                Spacer(modifier = modifier.width(4.dp))
                Text(text = currentDate, fontSize = 14.sp)
            }

        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.loading)
        )
    }
}