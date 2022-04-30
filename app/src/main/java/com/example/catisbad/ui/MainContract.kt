package com.example.catisbad.ui

import com.example.catisbad.model.AllCatsInstance
import com.example.catisbad.model.CatsData

interface MainContract {
    fun failure(message: String)
    fun showCatsData(data: AllCatsInstance)
}