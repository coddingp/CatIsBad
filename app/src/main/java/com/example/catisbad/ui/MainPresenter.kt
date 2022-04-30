package com.example.catisbad.ui

import android.renderscript.ScriptGroup
import android.util.Log
import com.example.catisbad.api.CatApi
import com.example.catisbad.model.CatsClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

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
            .enqueue(object : Callback<List<CatsClass>> {

                override fun onResponse(
                    call: Call<List<CatsClass>>,
                    response: Response<List<CatsClass>>
                ) {

                    if (response.isSuccessful && response.body() != null) {
                        val data = response.body()!!
                        Log.i("onRespomnse","${data}")
                        mainContract?.showCatsData(data)
                    }
                }

                override fun onFailure(call: Call<List<CatsClass>>, t: Throwable) {
                    Timber.tag("onFailure").i(t.message)
                    t.message?.let { mainContract?.failure(it) }
                }

            })
    }
}