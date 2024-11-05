package com.example.story.data

import com.example.story.data.Response.AddStory
import com.example.story.data.Response.DetailStory
import com.example.story.data.Response.Login
import com.example.story.data.Response.LoginRequest
import com.example.story.data.Response.Register
import com.example.story.data.Response.RegisterRequest
import com.example.story.data.Response.StoriesResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    @GET("stories")
    suspend fun getAllstories(@Header("Authorization") token: String): StoriesResponse

    @GET("stories/{id}")
    suspend fun getStorybyId(
        @Path("id") id: String,
        @Header("Authorization") token: String
    ): DetailStory

    @POST("register")
    fun register(
        @Body registerRequest: RegisterRequest
    ): Call<Register>

    @POST("login")
     fun login(
        @Body loginRequest: LoginRequest
    ): Call<Login>

     @Multipart
     @POST("stories")
     suspend fun addStory(
         @Header("Authorization") token: String,
         @Part photo: MultipartBody.Part,
         @Part("description") description: RequestBody,
     ) : AddStory

}