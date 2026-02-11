package com.example.lab08

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(
    navController: NavHostController,
    viewModel: StudentViewModel
) {
    val student = viewModel.selectedStudent
    var textFieldName by rememberSaveable { mutableStateOf(student?.std_name ?: "") }
    var textFieldAge by rememberSaveable { mutableStateOf(student?.std_age?.toString() ?: "") }

    val genderOptions = listOf("Male", "Female", "Other")
    val (selectedOption, onOptionSelected) = rememberSaveable {
        mutableStateOf(student?.std_gender ?: genderOptions[0])
    }

    var deleteDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(25.dp))
        Text(text = "Edit OR Delete a student", fontSize = 25.sp)

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            value = student?.std_id ?: "",
            onValueChange = {},
            label = { Text(text = "Student ID") },
            enabled = false
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            value = textFieldName,
            onValueChange = { textFieldName = it },
            label = { Text(text = "Student Name") }
        )

        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = "Student Gender :")
            Row(Modifier.fillMaxWidth()) {
                genderOptions.forEach { text ->
                    Row(
                        Modifier
                            .weight(1f)
                            .selectable(
                                selected = (text == selectedOption),
                                onClick = { onOptionSelected(text) },
                                role = Role.RadioButton
                            )
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (text == selectedOption),
                            onClick = null
                        )
                        Text(
                            text = text,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                }
            }
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            value = textFieldAge,
            onValueChange = { textFieldAge = it },
            label = { Text(text = "Student age") }
        )

        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                modifier = Modifier.width(100.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                onClick = { deleteDialog = true }
            ) {
                Text(text = "Delete")
            }
            Button(
                modifier = Modifier.width(100.dp),
                onClick = {
                    viewModel.updateStudent(
                        Student(
                            student?.std_id ?: "",
                            textFieldName,
                            selectedOption,
                            textFieldAge.toIntOrNull() ?: 0
                        )
                    ) {
                        navController.popBackStack()
                    }
                }
            ) {
                Text(text = "Update")
            }
            Button(
                modifier = Modifier.width(100.dp),
                onClick = { navController.popBackStack() }
            ) {
                Text(text = "Cancel")
            }
        }
    }

    if (deleteDialog) {
        AlertDialog(
            onDismissRequest = { deleteDialog = false },
            title = { Text(text = "Warning") },
            text = { Text(text = "Do you want to delete student: ${student?.std_id}?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        deleteDialog = false
                        viewModel.deleteStudent(student?.std_id ?: "") {
                            navController.popBackStack()
                        }
                    }
                ) {
                    Text("Yes")
                }
            },
            dismissButton = {
                TextButton(onClick = { deleteDialog = false }) {
                    Text("No")
                }
            }
        )
    }
}
