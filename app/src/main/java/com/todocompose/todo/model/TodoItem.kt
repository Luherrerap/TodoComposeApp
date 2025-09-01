package com.todocompose.todo.model

data class TodoItem(
    val id: Int,
    val title: String,
    val description: String,
    val done: Boolean
)