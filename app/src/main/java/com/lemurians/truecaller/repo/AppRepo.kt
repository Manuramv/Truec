package com.lemurians.truecaller.repo

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class AppRepo {
    var apiInterface: ApiInterface?=null
    init {
        apiInterface = ApiClient.getApiClientInterface().create(ApiInterface::class.java)
    }

    fun getBlogData(onSuccess: (String) -> Unit, onError : (String) -> Unit){
        apiInterface?.getBlogData()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                result -> onSuccess.invoke(result)
            },{
                error -> onError.invoke(error.toString())
            })
    }
}
