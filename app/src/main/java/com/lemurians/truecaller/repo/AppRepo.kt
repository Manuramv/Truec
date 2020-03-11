package com.lemurians.truecaller.repo

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.operators.single.SingleDoOnSuccess
import io.reactivex.schedulers.Schedulers
import okhttp3.Response
import okhttp3.ResponseBody

class AppRepo {
    var apiInterface: ApiInterface?=null
    init {
        apiInterface = ApiClient.getApiClientInterface().create(ApiInterface::class.java)
    }

    fun getBlogData(type:String,onSuccess: (ResponseBody) -> Unit, onError : (String) -> Unit){
        apiInterface?.getBlogData(type)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                result -> onSuccess.invoke(result)
            },{
                error -> onError.invoke(error.toString())
            })
    }
}
