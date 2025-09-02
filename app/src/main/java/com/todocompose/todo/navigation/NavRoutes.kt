package com.todocompose.todo.navigation

sealed class Screen (val route: String){
    object Home : Screen("home")
    object List : Screen("list")
    object Edit : Screen("edit")
}