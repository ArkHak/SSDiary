package ru.mys_ya.ssdiary.ui.screens.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
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
    onSelectTask: (Int) -> Unit,
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
                onSelectTask = { id ->
                    onSelectTask(id)
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun TaskListScreen(
    tasks: List<Task>,
    modifier: Modifier = Modifier,
    onSelectTask: (Int) -> Unit,
) {
    AnimatedContent(
        targetState = tasks,
        transitionSpec = {
            if (targetState != initialState) {
                slideInVertically { -it } with slideOutVertically { it }
            } else {
                slideInVertically { it } with slideOutVertically { -it }
            }
        }
    )
    { items ->
        LazyColumn(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(items) { item ->
                ItemTask(
                    task = item,
                    onClickItem = {
                        onSelectTask(item.id)
                    }
                )
            }
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