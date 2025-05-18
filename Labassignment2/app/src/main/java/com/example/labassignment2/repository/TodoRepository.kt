package com.example.labassignment2.repository

import com.example.labassignment2.data.TodoDao
import com.example.labassignment2.data.TodoEntity
import com.example.labassignment2.network.TodoApiService
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val api: TodoApiService,
    private val dao: TodoDao
) {

    private val sampleTodos = listOf(
        TodoEntity(
            userId = 1,
            id = 1,
            title = "Complete Android ",
            completed = false
        ),
        TodoEntity(
            userId = 1,
            id = 2,
            title = "Buy groceries",
            completed = true
        ),
        TodoEntity(
            userId = 1,
            id = 3,
            title = "Schedule dentist appointment",
            completed = false
        ),
        TodoEntity(
            userId = 1,
            id = 4,
            title = "Read a book for 30 minutes",
            completed = false
        ),
        TodoEntity(
            userId = 1,
            id = 5,
            title = "Go to the gym",
            completed = true
        ),
        TodoEntity(
            userId = 1,
            id = 6,
            title = "Call mom",
            completed = false
        )
    )

    suspend fun getTodos(): List<TodoEntity> {
        dao.deleteAllTodos() // Clear existing data
        dao.insertTodos(sampleTodos) // Insert sample data
        val todos = dao.getAllTodos()
        println("Todos loaded: $todos") // Debug log
        return todos
    }

    suspend fun getTodoById(id: Int): TodoEntity? {
        dao.deleteAllTodos() // Clear existing data
        dao.insertTodos(sampleTodos) // Insert sample data
        val todos = dao.getAllTodos()
        println("Todos loaded for ID $id: $todos") // Debug log
        return todos.find { it.id == id }
    }
}