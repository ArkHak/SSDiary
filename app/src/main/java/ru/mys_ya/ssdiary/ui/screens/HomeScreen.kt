package ru.mys_ya.ssdiary.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import io.github.boguszpawlowski.composecalendar.SelectableWeekCalendar
import io.github.boguszpawlowski.composecalendar.WeekCalendarState
import io.github.boguszpawlowski.composecalendar.rememberSelectableWeekCalendarState
import io.github.boguszpawlowski.composecalendar.selection.DynamicSelectionState
import ru.mys_ya.ssdiary.R

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onSelectTask: (String) -> Unit,
) {
    val calendarState = rememberSelectableWeekCalendarState()

    Column(
        modifier = modifier.padding(dimensionResource(id = R.dimen.default_padding))
    ) {
        Calendar(calendarState = calendarState)
        ItemTask(
            id = calendarState.selectionState.selection.joinToString { it.toString() },
            onClickItem = { onSelectTask(it) }
        )
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
fun ItemTask(
    id: String,
    modifier: Modifier = Modifier,
    onClickItem: (String) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Box(modifier = modifier
            .clickable {
                onClickItem(id)
            }) {
            Column {
                Text(text = "Название")
                Text(text = id)
            }
        }
    }
}