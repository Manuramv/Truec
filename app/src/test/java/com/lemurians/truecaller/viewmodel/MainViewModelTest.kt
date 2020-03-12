package com.lemurians.truecaller.viewmodel

import com.lemurians.truecaller.MainViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.plugins.RxAndroidPlugins




@RunWith(JUnit4::class)
class MainViewModelTest{
    var mainViewModel = MainViewModel()

    @Before
    open fun setUp(){
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }
    }

    @Test
    fun `fetch all three apis`(){
        Assert.assertNotNull( mainViewModel.callApisParallely())
    }
    @Test
    fun `fetch first API`(){
            Assert.assertNotNull( mainViewModel.callFirstApi())
    }

    @Test
     fun `fetch second API`(){
            Assert.assertNotNull( mainViewModel.callSecondApi())
     }

    @Test
    fun `fetch third API`(){
            Assert.assertNotNull( mainViewModel.callThirdApi())
    }


}
