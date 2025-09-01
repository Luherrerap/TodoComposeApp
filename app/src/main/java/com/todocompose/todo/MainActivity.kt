package com.todocompose.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.todocompose.todo.ui.screens.HomeScreen
import com.todocompose.todo.ui.screens.TodoEditScreen
import com.todocompose.todo.ui.screens.TodoListScreen
import com.todocompose.todo.ui.theme.TodoComposeTheme
import com.todocompose.todo.viewmodel.TodoViewModel

enum class Screen { Home, List, Edit }

class MainActivity : ComponentActivity() {
    private val vm: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TodoComposeTheme {
                var screen by rememberSaveable { mutableStateOf(Screen.Home) }

                Box(Modifier) {
                    when (screen) {
                        Screen.Home -> HomeScreen(
                            onEnterTasks = { screen = Screen.List }
                        )

                        Screen.List -> TodoListScreen(
                            items = vm.items,
                            onToggle = { vm.toggleDone(it) },
                            onDelete = { vm.delete(it) },
                            onEdit = { item ->
                                vm.startEditing(item)
                                screen = Screen.Edit
                            },
                            onCreate = {
                                vm.startEditing(null)
                                screen = Screen.Edit
                            },
                            onBack = { screen = Screen.Home }
                        )

                        Screen.Edit -> TodoEditScreen(
                            item = vm.editing,
                            onSave = { title, description ->
                                vm.save(title, description)
                                screen = Screen.List
                            },
                            onCancel = {
                                vm.startEditing(null)
                                screen = Screen.List
                            }
                        )
                    }
                }
            }
        }
    }
}
