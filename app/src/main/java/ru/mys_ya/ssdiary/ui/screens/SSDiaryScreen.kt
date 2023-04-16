package ru.mys_ya.ssdiary.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.koinViewModel
import ru.mys_ya.ssdiary.ui.screens.task.CreateTaskScreen
import ru.mys_ya.ssdiary.ui.screens.task.DetailTaskScreen
import ru.mys_ya.ssdiary.ui.screens.home.HomeScreen
import ru.mys_ya.ssdiary.ui.screens.home.HomeViewModel
import ru.mys_ya.ssdiary.ui.screens.task.TaskDetailViewModel
import ru.mys_ya.ssdiary.R


enum class SSDiaryScreen(@StringRes val title: Int) {
    Home(title = R.string.app_name),
    DetailScreen(title = R.string.detail_task_screen),
    CreateScreen(title = R.string.create_task_screen)
}

@Composable
fun SSDiaryApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = SSDiaryScreen.valueOf(
        backStackEntry?.destination?.route ?: SSDiaryScreen.Home.name
    )
    val homeViewModel = koinViewModel<HomeViewModel>()
    val taskDetailViewModel = koinViewModel<TaskDetailViewModel>()

    Scaffold(
        topBar = {
            SSDiaryAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(SSDiaryScreen.CreateScreen.name) },
                modifier = Modifier.navigationBarsPadding()
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "CreateScreen",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = SSDiaryScreen.Home.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = SSDiaryScreen.Home.name) {
                HomeScreen(
                    homeUiState = homeViewModel.homeUiState,
                    onSelectTask = { id ->
                        taskDetailViewModel.getTask(id)
                        navController.navigate(SSDiaryScreen.DetailScreen.name)
                    },
                    onSelectDate = { timestamp ->
                        homeViewModel.getTaskList(timestamp)
                    }
                )
            }
            composable(route = SSDiaryScreen.DetailScreen.name) {
                DetailTaskScreen(
                    taskDetailUiState = taskDetailViewModel.taskDetailUiState
                )
            }
            composable(route = SSDiaryScreen.CreateScreen.name) {
                CreateTaskScreen(
                    navigateBack = { navController.popBackStack() },
                )
            }
        }
    }
}

@Composable
fun SSDiaryAppBar(
    currentScreen: SSDiaryScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}