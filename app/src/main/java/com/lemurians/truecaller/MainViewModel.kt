package com.lemurians.truecaller

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lemurians.truecaller.repo.AppRepo
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import retrofit2.adapter.rxjava2.Result.response
import android.R.string
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import org.jsoup.select.Collector.collect

import java.util.Arrays.asList
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import java.util.*
import org.jsoup.select.Collector.collect
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Build
import androidx.annotation.RequiresApi
import java.util.stream.Collectors
import java.util.stream.Stream







class MainViewModel : ViewModel() {
    val TAG = MainViewModel::class.java.canonicalName

    val tenthCharLiveData = MutableLiveData<Char>()
    val listCharLiveData = MutableLiveData<String>()
    val listStringLiveData = MutableLiveData<List<Char>>()
    lateinit var uniqueWordCount: LiveData<Map<String,Int>>
    val appRepo = AppRepo()

    init {

    }

    //This method wil call the API to load the blog content.
    fun fetchBlogContentForSIngleCharacter(onSuccess: () -> Unit, onError: (String) -> Unit) {
        appRepo.getBlogData({ response ->
            val document = Jsoup.parse(response.toString())
            findChar(document.allElements.html())
            findListOfChar(document.allElements.html())
        },{
            onError.invoke(it.toString())
        })
    }

    //this method will find the 10th element from the string.
    private fun findChar(content : String) {
        Log.d(TAG, "Contents ::::"+content)
        tenthCharLiveData.value = content[10]
    }

    //this method will return the every 10th element.
    private fun findListOfChar(content : String){
        val str =content.withIndex().filter { (i, value) -> (i!=0 && i % 10 == 0) }.map { (i, value) -> value }
        listCharLiveData.value = str.toString().substring(1,str.toString().length-1)

    }

}