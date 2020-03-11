package com.lemurians.truecaller.repo

import com.lemurians.truecaller.Constants
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiInterface {

    @GET(Constants.GET_BLOG_DATA)

    fun getBlogData() : Observable<ResponseBody>

}