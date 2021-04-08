package com.noah.architecturepractice.application

import android.app.Application
import com.noah.architecturepractice.dagger.AppComponent
import com.noah.architecturepractice.dagger.AppModule
import com.noah.architecturepractice.dagger.DaggerAppComponent

class DPApplication : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = initDagger(this)
    }

    private fun initDagger(app: DPApplication) : AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .build()
}