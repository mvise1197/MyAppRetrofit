package com.miguelvise.myappretrofit

import retrofit2.Call
import retrofit2.http.GET

interface GithubApiInterface {
    @GET("users")
    fun getUsers(): Call<List<UsersItem>>
}