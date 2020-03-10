package com.lemurians.truecaller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.canonicalName
    lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        //calling the Viewmodel to fetch the APis.
        btnCallApi.setOnClickListener({callApis()})

        fetchApiDetails();
    }

    private fun callApis() {
        mainViewModel.fetchBlogContent({
            //sucess dismiss the progressbar
        },{
            //error show the error alert
        })
    }

    private fun fetchApiDetails() {
        mainViewModel.tenthCharLiveData.observe(this, Observer{
            txtTenthchar.text = it
            Log.i(TAG, "Tenth character from the blog::"+it)

        })
    }
}
