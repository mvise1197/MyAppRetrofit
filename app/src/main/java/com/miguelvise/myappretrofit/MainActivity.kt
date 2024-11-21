package com.miguelvise.myappretrofit

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var rvMain: RecyclerView
    private lateinit var myAdapter: MyAdapter

    private var BASE_URL = "https://api.github.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        rvMain = findViewById(R.id.recycler_view)
        rvMain.layoutManager = LinearLayoutManager(this)

        getAllData()
    }

    private fun getAllData(){
        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubApiInterface::class.java)

        var retroData = retrofit.getUsers()

        retroData.enqueue(object : Callback<List<UsersItem>> {
            override fun onResponse(call: Call<List<UsersItem>>, response: Response<List<UsersItem>>) {
                if (response.isSuccessful) {
                    myAdapter = MyAdapter(baseContext, response.body()!!)
                    rvMain.adapter = myAdapter
                }
            }

            override fun onFailure(call: Call<List<UsersItem>>, t: Throwable) {
                Log.d("Error", t.message.toString())
            }
        })
    }
}