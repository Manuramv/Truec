package com.lemurians.truecaller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import android.text.method.ScrollingMovementMethod



class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.canonicalName
    lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        //calling the Viewmodel to fetch the APis.
        btnCallApi.setOnClickListener({callApis()})

        observeData();
    }

    private fun callApis() {
        mainViewModel.fetchBlogContentForSIngleCharacter({
            //sucess dismiss the progressbar
        },{
            //error show the error alert
        })
    }

    private fun observeData() {
        mainViewModel.tenthCharLiveData.observe(this, Observer{
            txtTenthchar.text = it.toString()
            Log.i(TAG, "Tenth character from the blog::"+it)
        })

        mainViewModel.listCharLiveData.observe(this, Observer{
            txtEveryTenth.text = it
            txtEveryTenth.movementMethod = ScrollingMovementMethod();
            Log.i(TAG, "List of every 10th character::"+it)
        })
    }
}
