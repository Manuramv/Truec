package com.lemurians.truecaller

import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.canonicalName
    lateinit var mainViewModel: MainViewModel


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        //calling the Viewmodel to fetch the APis.
        btnCallApi.setOnClickListener({callApis()})

        //this method will observe the changes and render the changes on ui.
        observeData();
    }

    private fun callApis() {
        mainViewModel.callApisParallely()
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
        mainViewModel.uniqueWordCount.observe(this, Observer{
            txtWordCounter.movementMethod = ScrollingMovementMethod();
            txtWordCounter.text = it
            Log.i(TAG, "Word counters list::"+it)
        })
    }
}
