package com.example.catisbad.ui

import android.util.Log
import com.example.catisbad.api.CatApi
import com.example.catisbad.model.AllCatsInstance
import com.example.catisbad.model.CatsClass
import com.example.catisbad.model.CatsData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val API_KEY = "78b6dd25-d96b-438d-85e3-e7dc21481d75"

class MainPresenter(private val catApi: CatApi) {
    private var mainContract: MainContract? = null

    fun attach(view: MainContract) {
        mainContract = view
    }

    fun detach(view: MainContract) {
        mainContract = null
    }

    fun getCatsData(apiKey: String) {

        catApi.getImagesData(API_KEY)
            .enqueue(object : Callback<AllCatsInstance> {

                override fun onResponse(call: Call<AllCatsInstance>, response: Response<AllCatsInstance>) {

                    Log.i("!onResponse", "${123}")
                    if (response.isSuccessful && response.body() != null) {
                        val data = response.body()!!
                        mainContract?.showCatsData(data)
                    }
                }

                override fun onFailure(call: Call<AllCatsInstance>, t: Throwable) {
                    Log.i("!onFailure", "${t.message}")
                    t.message?.let { mainContract?.failure(it) }
                }

            })
    }
}