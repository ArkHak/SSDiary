package ru.mys_ya.ssdiary.ui.screens.task

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Alarm
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Task
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import ru.mys_ya.ssdiary.R
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun CreateTaskScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    viewModel: TaskCreateViewModel = koinViewModel(),
) {
    val coroutineScope = rememberCoroutineScope()
    TaskCreateBody(
        taskUiState = viewModel.taskUiState,
        onTaskValueChange = viewModel::updateUiState,
        onSaveClick = {
            coroutineScope.launch {
                viewModel.saveTask()
                navigateBack()
            }
        },
        modifier = modifier
    )
}

@Composable
fun TaskCreateBody(
    taskUiState: TaskUiState,
    onTaskValueChange: (TaskUiState) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TaskInputForm(
            taskUiState = taskUiState,
            onTaskValueChange = onTaskValueChange,
            modifier = modifier
        )
        Button(
            onClick = onSaveClick,
            enabled = taskUiState.actionEnabled,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Save")
        }
    }
}

@Composable
fun TaskInputForm(
    taskUiState: TaskUiState,
    modifier: Modifier = Modifier,
    onTaskValueChange: (TaskUiState) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = taskUiState.name,
            onValueChange = { onTaskValueChange(taskUiState.copy(name = it)) },
            label = { Text(stringResource(R.string.name)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Task,
                    contentDescription = taskUiState.name,
                )
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        DateTimeField(
            taskUiState = taskUiState,
            modifier = modifier,
            onTaskValueChange = onTaskValueChange
        )
        OutlinedTextField(
            value = taskUiState.description,
            onValueChange = { onTaskValueChange(taskUiState.copy(description = it)) },
            label = { Text(stringResource(R.string.description)) },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
fun DateTimeField(
    taskUiState: TaskUiState,
    modifier: Modifier = Modifier,
    onTaskValueChange: (TaskUiState) -> Unit,
) {
    Column {
        TaskTimePicker(
            taskUiState = taskUiState,
            modifier = modifier,
            onTaskValueChange = onTaskValueChange
        )
        Spacer(modifier = Modifier.height(20.dp))
        TaskDatePicker(
            taskUiState,
            modifier = modifier,
            onTaskValueChange = onTaskValueChange
        )
    }
}

@Composable
fun TaskTimePicker(
    taskUiState: TaskUiState,
    modifier: Modifier = Modifier,
    onTaskValueChange: (TaskUiState) -> Unit,
) {
    val timeDialogState = rememberMaterialDialogState()
    var pickedTime by remember {
        mutableStateOf(LocalTime.now())
    }
    val formattedTime by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("HH:mm", Locale.getDefault())
                .format(pickedTime)
        }
    }

    Row(
        modifier = modifier
            .clickable { timeDialogState.show() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            imageVector = Icons.Rounded.Alarm,
            contentDescription = formattedTime,
            tint = Color.Gray
        )
        Text(text = formattedTime, fontSize = 16.sp)
    }

    MaterialDialog(
        dialogState = timeDialogState,
        buttons = {
            positiveButton(text = "Ok")
            negativeButton(text = "Cancel")
        }
    ) {
        timepicker(
            initialTime = LocalTime.now(),
            title = "Pick a date",
            is24HourClock = true,
        ) {
            pickedTime = it
            onTaskValueChange(taskUiState.copy(timeStart = it))
        }
    }
}

@Composable
fun TaskDatePicker(
    taskUiState: TaskUiState,
    modifier: Modifier = Modifier,
    onTaskValueChange: (TaskUiState) -> Unit,
) {
    val dateDialogState = rememberMaterialDialogState()
    var pickedDate by remember {
        mutableStateOf(LocalDate.now())
    }

    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("dd.MM.yyyy")
                .format(pickedDate)
        }
    }

    Row(
        modifier = modifier
            .clickable { dateDialogState.show() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            imageVector = Icons.Rounded.CalendarMonth,
            contentDescription = formattedDate,
            tint = Color.Gray
        )
        Text(text = formattedDate, fontSize = 16.sp)

        MaterialDialog(
            dialogState = dateDialogState,
            buttons = {
                positiveButton(text = "Ok") {}
                negativeButton(text = "Cancel")
            }
        ) {
            datepicker(
                initialDate = LocalDate.now(),
                title = "Pick a date",
                locale = Locale.getDefault()
            ) {
                pickedDate = it
                onTaskValueChange(taskUiState.copy(dateStart = it))
            }
        }
    }
}