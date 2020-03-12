package com.lemurians.truecaller.repo

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class AppRepoTest{
    companion object{
        fun setupTest() {
            RxAndroidPlugins.setInitMainThreadSchedulerHandler {  Schedulers.trampoline() }
        }
    }

    @Before
    open fun beforeEach() {
        MockitoAnnotations.initMocks(this)
        setupTest()
    }
    @Test
    fun `get the blog data`(){
        AppRepo().getBlogData({
            Assert.assertTrue(it != null)
        },{ assert(false)})
    }

}

