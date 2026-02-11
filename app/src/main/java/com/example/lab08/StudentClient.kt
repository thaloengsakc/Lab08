package com.example.lab08

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object StudentClient {
    // ใช้ 10.0.2.2 สำหรับ Android Emulator เพื่อเชื่อมต่อกับ localhost (127.0.0.1) ของเครื่องคอมพิวเตอร์
    private const val BASE_URL = "http://10.0.2.2:3000/"
    
    val studentAPI: StudentAPI by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StudentAPI::class.java)
    }
}
