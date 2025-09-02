package com.todocompose.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.todocompose.todo.navigation.TodoNavGraph
import com.todocompose.todo.ui.theme.TodoComposeTheme
import com.todocompose.todo.viewmodel.TodoViewModel

class MainActivity : ComponentActivity() {
    private val vm: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TodoComposeTheme {
                Surface {
                    val navController = rememberNavController()
                    TodoNavGraph(navController = navController, vm = vm)
                }
            }
        }
    }
}
