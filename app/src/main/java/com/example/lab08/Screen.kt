package com.example.lab08

sealed class Screen(val route: String, val name: String) {
    data object Home : Screen("home_screen", "Home")
    data object Insert : Screen("insert_screen", "Insert")
    data object Edit : Screen("edit_screen", "Edit")
}
