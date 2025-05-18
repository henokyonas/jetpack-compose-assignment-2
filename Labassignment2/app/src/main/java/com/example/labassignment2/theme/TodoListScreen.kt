package com.example.labassignment2.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.labassignment2.TodoListViewModel
import com.example.labassignment2.TodoUiState
import androidx.compose.ui.Alignment

@Composable
fun TodoListScreen(
    viewModel: TodoListViewModel,
    onTodoClick: (Int) -> Unit
) {
    val uiState = viewModel.uiState.collectAsState().value

    when (uiState) {
        is TodoUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is TodoUiState.Error -> {
            Text("Error: ${uiState.message}")
        }

        is TodoUiState.Success -> {
            LazyColumn(modifier = Modifier.padding(16.dp)) {
                items(uiState.todos) { todo ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable { onTodoClick(todo.id) }
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = todo.title, style = MaterialTheme.typography.titleMedium)
                            Text(
                                text = if (todo.completed) "Completed" else "Pending",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}
