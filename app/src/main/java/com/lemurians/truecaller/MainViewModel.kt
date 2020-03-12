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
import android.util.ArrayMap
import com.lemurians.truecaller.Constants.REGEX_DELIMITTER
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
    val TAG = MainViewModel::class.java.canonicalName

    val tenthCharLiveData = MutableLiveData<Char>()
    val listCharLiveData = MutableLiveData<String>()
    val uniqueWordCount = MutableLiveData<Map<String,Int>>()
    val appRepo = AppRepo()

    init {

    }

    //This method wil call the API to load the blog content.
   /* fun fetchBlogContentForSIngleCharacter(onSuccess: () -> Unit, onError: (String) -> Unit) {
        appRepo.getBlogData({ response ->
            Log.d(TAG,"response from retrofit:"+ response)
            findChar(response)
            findListOfChar(response)
            wordCounter(response)
        },{
            onError.invoke(it.toString())
        })
    }*/


    fun callNetworkParallely(){
        GlobalScope.launch{
            callFirstApi()
            callSecondApi()
            callThirdApi()
        }
    }

    fun callFirstApi(){
        appRepo.getBlogData({ response ->
            Log.d(TAG,"response from retrofit:"+ response)
            findChar(response)
        },{
            //error will process later
        })
    }

    fun callSecondApi(){
        appRepo.getBlogData({ response ->
            Log.d(TAG,"response from retrofit:"+ response)
            findListOfChar(response)
        },{
            //error will process later
        })
    }
    fun callThirdApi(){
            appRepo.getBlogData({ response ->
                Log.d(TAG,"response from retrofit:"+ response)
                wordCounter(response)
            },{
                //error will process later
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

        val words = "one two three four five six seven eight nine ten".split(REGEX_DELIMITTER.toRegex())
        val frequenciesByFirstChar = content.split(" "). groupingBy { it }.eachCount()

        Log.d(TAG,"list:::"+frequenciesByFirstChar)

        val wordsArr = content.split(REGEX_DELIMITTER.toRegex(RegexOption.IGNORE_CASE))
         var arrMap :  MutableMap<String, Int>? = TreeMap<String,Int> (String.CASE_INSENSITIVE_ORDER)


        for(word:String in wordsArr){
            if( arrMap!=null &&   arrMap?.containsKey(word)){
                val count  = (arrMap.get(word))?.plus(1)
                    arrMap.put(word,count!!)
                } else {
                arrMap?.put(word, 1)
            }
        }

        val occurrences = content.split("").filter{ it in content}
            .groupingBy { it }
            .eachCount()

        uniqueWordCount.value = arrMap

        Log.d(TAG,"occurrences:::"+arrMap)

        //arrMap?.forEach { (key, value) -> Log.d(TAG,"key==="+key+"..value=="+value) }

        for ((key, value) in arrMap!!) {
            Log.d(TAG,"key==="+key+"..value=="+value)
        }

    }

}