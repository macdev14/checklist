package pt.ipca.alunos.checklist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import pt.ipca.alunos.checklist.ui.theme.ChecklistTheme

@Composable
fun App(modifier: Modifier = Modifier, viewModel: ChecklistViewModel) {
    Home(modifier = modifier, viewModel = viewModel)
}

@Composable
fun Home(modifier: Modifier = Modifier, viewModel: ChecklistViewModel) {
    val items by viewModel.items.collectAsState()
    var newItemName by remember { mutableStateOf("") }

    Column(modifier = modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = newItemName,
                onValueChange = { newItemName = it },
                placeholder = { Text("Enter item name") },
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {
                    if (newItemName.isNotBlank()) {
                        viewModel.addItem(newItemName)
                        newItemName = ""
                    }
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(items) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = item.name)
                    Button(
                        onClick = { viewModel.deleteItem(item) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text("Delete", color = Color.White)
                    }
                }
            }
        }
    }
}