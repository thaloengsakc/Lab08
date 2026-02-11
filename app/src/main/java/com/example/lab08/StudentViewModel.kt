package com.example.lab08

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class StudentViewModel : ViewModel() {
    var studentList = mutableStateListOf<Student>()
        private set

    var selectedStudent by mutableStateOf<Student?>(null)

    fun getAllStudents() {
        viewModelScope.launch {
            try {
                val response = StudentClient.studentAPI.retrieveStudent()
                studentList.clear()
                studentList.addAll(response)
            } catch (e: Exception) {
                Log.e("StudentViewModel", "Get Error: ${e.message}")
            }
        }
    }

    fun insertStudent(student: Student, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                StudentClient.studentAPI.insertStudent(
                    std_id = student.std_id,
                    std_name = student.std_name,
                    std_gender = student.std_gender,
                    std_age = student.std_age
                )
                onSuccess()
                getAllStudents()
            } catch (e: Exception) {
                Log.e("StudentViewModel", "Insert Error: ${e.message}")
            }
        }
    }

    fun updateStudent(student: Student, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                StudentClient.studentAPI.updateStudent(
                    std_id = student.std_id,
                    std_name = student.std_name,
                    std_gender = student.std_gender,
                    std_age = student.std_age
                )
                onSuccess()
                getAllStudents()
            } catch (e: Exception) {
                Log.e("StudentViewModel", "Update Error: ${e.message}")
            }
        }
    }

    fun deleteStudent(std_id: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                StudentClient.studentAPI.deleteStudent(std_id)
                onSuccess()
                getAllStudents()
            } catch (e: Exception) {
                Log.e("StudentViewModel", "Delete Error: ${e.message}")
            }
        }
    }
}
