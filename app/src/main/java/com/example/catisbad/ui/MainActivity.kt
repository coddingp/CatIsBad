package com.example.catisbad.ui

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.catisbad.R
import com.example.catisbad.api.CatApi
import com.example.catisbad.api.CatRequest
import com.example.catisbad.databinding.ActivityMainBinding
import com.example.catisbad.model.CatsClass
import timber.log.Timber

private const val API_KEY = "78b6dd25-d96b-438d-85e3-e7dc21481d75"

class MainActivity : AppCompatActivity(), MainContract {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val api: CatApi = CatRequest.getRetrofit().create(CatApi::class.java)
    private val presenter: MainPresenter = MainPresenter(api)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
//        backgroundAnimation()

        presenter.getCatsData(API_KEY)
        presenter.attach(this@MainActivity)
        val catsClass: CatsClass
//        val catsList:MutableList<CatsClass> = mutableListOf(catsClass)
        with(binding) {
            setContentView(binding.root)

            imageButton.setOnClickListener {
                Glide.with(llMain.context)
                    .load("https://theconversation.com/physics-and-psychology-of-cats-an-improbable-conversation-176020")
                    .into(imageView)
                imageButton.animate().apply {
                    rotationBy(360f)
                    duration = 1000
                    presenter.getCatsData(API_KEY)
                    Timber.tag("SetOnClickListener").i("Works")
                }.start()
                imageView.visibility = View.VISIBLE
                imageView.setImageResource(R.drawable.cat)
            }
        }
    }

    private fun backgroundAnimation() {
        setContentView(binding.root)
        binding.apply {
            val animationDrawable: AnimationDrawable =
                imageButton.background as AnimationDrawable
            animationDrawable.apply {
                setEnterFadeDuration(1000)
                setExitFadeDuration(3000)
                start()
            }
        }
    }

    override fun failure(message: String) {
        error(message)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach(this)
    }

    override fun showCatsData(data: List<CatsClass>) = with(binding) {
        setContentView(binding.root)
    }

    fun onClick(view: View) {
        binding.apply {
            setContentView(binding.root)
            presenter.getCatsData(API_KEY)
        }
    }
}