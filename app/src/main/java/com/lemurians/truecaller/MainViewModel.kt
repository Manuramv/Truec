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
import pl.droidsonroids.jspoon.Jspoon
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.icu.lang.UCharacter.GraphemeClusterBreak.T














class MainViewModel : ViewModel() {
    val TAG = MainViewModel::class.java.canonicalName

    val tenthCharLiveData = MutableLiveData<Char>()
    val listCharLiveData = MutableLiveData<String>()
    lateinit var uniqueWordCount: LiveData<Map<String,Int>>
    val appRepo = AppRepo()

    init {

    }

    //This method wil call the API to load the blog content.
    fun fetchBlogContentForSIngleCharacter(onSuccess: () -> Unit, onError: (String) -> Unit) {
        appRepo.getBlogData({ response ->

            Log.d(TAG,"response from retrofit:"+ response)

           /* val document = Jsoup.parse(response.toString())

            Log.d(TAG,"whole string:::document.allElements:::-->"+document.allElements.toString())

            Log.d(TAG,"whole string:::document.allElements.html():::-->"+document.allElements.html())

            Log.d(TAG,"whole string:::document.wholeText():::-->"+document.wholeText())


            Log.d(TAG,"whole string:::document.title():::-->"+document.title())
            Log.d(TAG,"whole string:::document.body():::-->"+document.body().allElements)

            val webPage = "http://www.aboullaite.com"

            val wholeContent = document.allElements.html()+document.body().html()
            val abcd = document.wholeText()
            //Log.d(TAG,"whole string:::"+abcd)
*/

            findChar(response)
            findListOfChar(response)
            wordCounter(response)
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

    //this method will find the occurence of the word.
    private fun wordCounter(content : String){

        val words = "one two three four five six seven eight nine ten".split(' ')
        val frequenciesByFirstChar = content.split(" "). groupingBy { it }.eachCount()

        Log.d(TAG,"list:::"+frequenciesByFirstChar)

        val occurrences = content.split("[ \\t\\n]+").filter{ it in content}
            .groupingBy { it }
            .eachCount()

        Log.d(TAG,"occurrences:::"+occurrences)


    }

}