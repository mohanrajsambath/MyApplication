package com.task.nebenan.view.ui

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.task.nebenan.R
import com.task.nebenan.viewmodel.MyViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class Neighbour_Screen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lateinit var mModel: MyViewModel

        // get the view model
        mModel = ViewModelProviders.of(this).get(MyViewModel::class.java)

        // Create the observe to updates Ui

        val randomNumber = Observer<Int>{newNumber ->

            TxtView_text.text = "Current Number : $newNumber"

        }

        mModel.currentRandomNumber.observe(this,randomNumber)

        // Button click listener
        btn_RandomNumber.setOnClickListener{
            // Change the data
            mModel.currentRandomNumber.value = Random.nextInt(50)
        }
    }
}
