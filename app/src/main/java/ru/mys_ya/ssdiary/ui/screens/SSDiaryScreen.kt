package ru.mys_ya.ssdiary.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.TableRows
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    CreateScreen(title = R.string.create_task_screen),
}

@Composable
fun SSDiaryApp(
    modifier: Modifier = Modifier,
    ssDiaryScreenViewModel: SSDiaryScreenViewModel = koinViewModel(),
) {
    SSDiaryApp(
        modifier = modifier,
        uiState = ssDiaryScreenViewModel.uiState,
        changeFabEnable = ssDiaryScreenViewModel::changeFabEnable,
        changeSettingsEnabled = ssDiaryScreenViewModel::changeSettingsEnable,
        changeTasksView = ssDiaryScreenViewModel::changeTasksView
    )
}

@Composable
fun SSDiaryApp(
    uiState: SSDiaryScreenUiState,
    changeFabEnable: (Boolean) -> Unit,
    changeSettingsEnabled: (Boolean) -> Unit,
    changeTasksView: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = SSDiaryScreen.valueOf(
        backStackEntry?.destination?.route ?: SSDiaryScreen.Home.name
    )
    val showFab = uiState.isFabEnable
    val settingsShow = uiState.isSettingsEnable
    val tasksView = uiState.isTaskTableView
    val homeViewModel = koinViewModel<HomeViewModel>()
    val taskDetailViewModel = koinViewModel<TaskDetailViewModel>()
    var timestampHomeScreen = 0L

    Scaffold(
        topBar = {
            SSDiaryAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                settingsShow = settingsShow,
                navigateSettings = {
                    changeTasksView(!tasksView)
                }
            )
        },
        floatingActionButton = {
            if (showFab) {
                FloatingActionButton(
                    onClick = { navController.navigate(SSDiaryScreen.CreateScreen.name) },
                    modifier = Modifier.navigationBarsPadding(),
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "CreateScreen",
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = SSDiaryScreen.Home.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = SSDiaryScreen.Home.name) {
                homeViewModel.getTaskList(timestampHomeScreen)
                changeFabEnable(true)
                changeSettingsEnabled(true)
                HomeScreen(
                    homeUiState = homeViewModel.homeUiState,
                    isTasksTableView = tasksView,
                    onSelectTask = { id ->
                        taskDetailViewModel.getTask(id)
                        navController.navigate(SSDiaryScreen.DetailScreen.name)
                    },
                    onSelectDate = { timestamp ->
                        timestampHomeScreen = timestamp
                        homeViewModel.getTaskList(timestampHomeScreen)
                    }
                )
            }
            composable(route = SSDiaryScreen.DetailScreen.name) {
                changeFabEnable(false)
                changeSettingsEnabled(false)
                DetailTaskScreen(
                    taskDetailUiState = taskDetailViewModel.taskDetailUiState
                )
            }
            composable(route = SSDiaryScreen.CreateScreen.name) {
                changeFabEnable(false)
                changeSettingsEnabled(false)
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
    settingsShow: Boolean,
    navigateUp: () -> Unit,
    navigateSettings: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        modifier = modifier
            .background(color = MaterialTheme.colors.primary),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        },
        actions = {
            if (settingsShow) {
                IconButton(onClick = {
                    navigateSettings()
                }) {
                    Icon(
                        imageVector = Icons.Filled.TableRows,
                        contentDescription = stringResource(R.string.settings),
                        tint = Color.White
                    )
                }
            }
        }
    )
}