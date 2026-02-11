package com.example.lab08

import com.google.gson.annotations.SerializedName

data class Student(
    @SerializedName("std_id") val std_id: String = "",
    @SerializedName("std_name") val std_name: String = "",
    @SerializedName("std_gender") val std_gender: String = "",
    @SerializedName("std_age") val std_age: Int = 0
)
