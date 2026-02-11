package com.example.lab08

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.DELETE
import retrofit2.http.Path

interface StudentAPI {
    @GET("allStd")
    suspend fun retrieveStudent(): List<Student>

    @FormUrlEncoded
    @POST("insertStd")
    suspend fun insertStudent(
        @Field("std_id") std_id: String,
        @Field("std_name") std_name: String,
        @Field("std_gender") std_gender: String,
        @Field("std_age") std_age: Int
    ): Student

    @FormUrlEncoded
    @PUT("updateStd/{std_id}")
    suspend fun updateStudent(
        @Path("std_id") std_id: String,
        @Field("std_name") std_name: String,
        @Field("std_gender") std_gender: String,
        @Field("std_age") std_age: Int
    ): Student

    @DELETE("deleteStd/{std_id}")
    suspend fun deleteStudent(
        @Path("std_id") std_id: String
    ): Student
}
