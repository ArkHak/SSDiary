package ru.mys_ya.ssdiary.ui.screens.task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Task
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import ru.mys_ya.ssdiary.R

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
        modifier = modifier.padding(8.dp)
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
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        TaskInputForm(
            taskUiState = taskUiState,
            onValueChange = onTaskValueChange
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
    onValueChange: (TaskUiState) -> Unit = {},
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = taskUiState.name,
            onValueChange = { onValueChange(taskUiState.copy(name = it)) },
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
        OutlinedTextField(
            value = taskUiState.description,
            onValueChange = { onValueChange(taskUiState.copy(description = it)) },
            label = { Text(stringResource(R.string.description)) },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}