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


class MainViewModel : ViewModel() {
    val TAG = MainViewModel::class.java.canonicalName

    val tenthCharLiveData= MutableLiveData<Char>()
    lateinit var listCharLiveData: MutableList<String>
    lateinit var uniqueWordCount: LiveData<Map<String,Int>>
    val hValue = "application/json"
    val appRepo = AppRepo()

    init {

    }


    fun fetchBlogContent(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        appRepo.getBlogData(hValue,{response ->
            //need to write the method to found the 10th Character.

            val html = response.string()
            val document = Jsoup.parse(html)
            findChar(document.allElements.html())

        },{
            onError.invoke(it.toString())
        })
    }

    private fun findChar(content : String) {
        Log.d(TAG, "Contents ::::"+content)
        tenthCharLiveData.value = content[10]
    }

}