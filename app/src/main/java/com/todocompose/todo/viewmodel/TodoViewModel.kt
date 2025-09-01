package com.todocompose.todo.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.todocompose.todo.model.TodoItem

class TodoViewModel : ViewModel() {
    private val _items = mutableStateListOf<TodoItem>()
    val items: List<TodoItem> get() = _items

    var editing: TodoItem? by mutableStateOf(null)
        private set

    private var nextId = 0

    init {

        _items.addAll(
            listOf(
                TodoItem(nextId++, "Probar app", "Crear tu primer TODO", false),
                TodoItem(nextId++, "Leer docs", "Revisar Compose", true)
            )
        )
    }

    fun startEditing(item: TodoItem?) {
        editing = item
    }

    fun save(title: String, description: String) {
        val t = title.trim()
        val d = description.trim()
        if (t.isEmpty()) return
        val e = editing
        if (e == null) {
            // CREATE
            _items.add(TodoItem(nextId++, t, d, false))
        } else {
            // UPDATE
            val idx = _items.indexOfFirst { it.id == e.id }
            if (idx != -1) _items[idx] = e.copy(title = t, description = d)
        }
        editing = null
    }

    fun delete(item: TodoItem) {
        _items.removeAll { it.id == item.id }
    }

    fun toggleDone(item: TodoItem) {
        val idx = _items.indexOfFirst { it.id == item.id }
        if (idx != -1) _items[idx] = _items[idx].copy(done = !_items[idx].done)
    }
}
