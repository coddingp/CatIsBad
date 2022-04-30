package com.example.catisbad.ui

import com.example.catisbad.model.CatsClass

interface MainContract {
    fun failure(message: String)
    fun showCatsData(data: List<CatsClass>)
}