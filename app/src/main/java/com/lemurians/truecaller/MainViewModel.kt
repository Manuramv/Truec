package com.lemurians.truecaller

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lemurians.truecaller.repo.AppRepo

class MainViewModel : ViewModel() {

    val tenthCharLiveData= MutableLiveData<String>()
    lateinit var listCharLiveData: MutableList<String>
    lateinit var uniqueWordCount: LiveData<Map<String,Int>>
    val appRepo = AppRepo()

    init {
        tenthCharLiveData.value = "Hi Truecaller"
    }


    fun fetchBlogContent(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        appRepo.getBlogData({ response ->
            //need to write the method to found the 10th Character.
            tenthCharLiveData.postValue(response.toString())
        }, {
            onError.invoke(it)
        })
    }

}