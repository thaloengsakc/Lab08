package com.example.lab08

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun InsertScreen(
    navController: NavHostController,
    viewModel: StudentViewModel = viewModel()
) {
    var textFieldID by rememberSaveable { mutableStateOf("") }
    var textFieldName by rememberSaveable { mutableStateOf("") }
    var textFieldAge by rememberSaveable { mutableStateOf("") }
    
    // Gender RadioButton State
    val genderOptions = listOf("Male", "Female", "Other")
    val (selectedOption, onOptionSelected) = rememberSaveable { mutableStateOf(genderOptions[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(25.dp))
        Text(text = "Insert New Student", fontSize = 25.sp)

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            value = textFieldID,
            onValueChange = { textFieldID = it },
            label = { Text(text = "Student ID") }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            value = textFieldName,
            onValueChange = { textFieldName = it },
            label = { Text(text = "Student Name") }
        )

        // Gender Selection
        Column(Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)) {
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
                            onClick = null // null because of selectable modifier
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
            label = { Text(text = "Student Age") }
        )

        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier
                    .width(150.dp)
                    .padding(5.dp),
                onClick = {
                    viewModel.insertStudent(
                        Student(
                            std_id = textFieldID,
                            std_name = textFieldName,
                            std_gender = selectedOption,
                            std_age = textFieldAge.toIntOrNull() ?: 0
                        )
                    ) {
                        navController.popBackStack()
                    }
                }
            ) {
                Text(text = "Save")
            }
            Button(
                modifier = Modifier
                    .width(150.dp)
                    .padding(5.dp),
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Text(text = "Cancel")
            }
        }
    }
}
