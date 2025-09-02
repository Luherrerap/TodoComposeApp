package com.todocompose.todo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.todocompose.todo.ui.screens.HomeScreen
import com.todocompose.todo.ui.screens.TodoEditScreen
import com.todocompose.todo.ui.screens.TodoListScreen
import com.todocompose.todo.viewmodel.TodoViewModel

@Composable
fun TodoNavGraph(
    navController: NavHostController,
    vm: TodoViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onEnterTasks = { navController.navigate(Screen.List.route) }
            )
        }

        composable(Screen.List.route) {
            TodoListScreen(
                items = vm.items,
                onToggle = { vm.toggleDone(it) },
                onDelete = { vm.delete(it) },
                onEdit = { item ->
                    vm.startEditing(item)
                    navController.navigate(Screen.Edit.route)
                },
                onCreate = {
                    vm.startEditing(null)
                    navController.navigate(Screen.Edit.route)
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Edit.route) {
            TodoEditScreen(
                item = vm.editing,
                onSave = { title, description ->
                    vm.save(title, description)
                    navController.popBackStack() // vuelve a la lista
                },
                onCancel = {
                    vm.startEditing(null)
                    navController.popBackStack()
                }
            )
        }
    }
}