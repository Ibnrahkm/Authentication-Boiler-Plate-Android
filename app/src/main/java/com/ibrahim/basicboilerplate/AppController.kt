package com.ibrahim.basicboilerplate

import android.app.Activity
import android.app.Application
import android.content.SharedPreferences
import com.ibrahim.basicboilerplate.dagger.Component.AppComponent
import com.ibrahim.basicboilerplate.dagger.Component.DaggerAppComponent
import com.ibrahim.basicboilerplate.dagger.Module.ContextModule
import com.ibrahim.basicboilerplate.dagger.Module.RetrofitModule
import com.ibrahim.basicboilerplate.util.Helper
import java.util.*

class AppController : Application() {

    lateinit var component: AppComponent

    var isInternetAvailable = true



    override fun onCreate() {
        super.onCreate()


        component = DaggerAppComponent.builder().retrofitModule(RetrofitModule())
            .contextModule(ContextModule(applicationContext)).build()
        component.inject(this@AppController)
        checkInternetAvailablity()
    }

    companion object {
        fun get(context: Activity): AppController {
            return context.application as AppController
        }
    }


    fun getApplicationComponent(): AppComponent {
        return component
    }

    fun checkInternetAvailablity() {
        Timer().schedule(object : TimerTask() {
            override fun run() {
                try {
                    val process = Runtime.getRuntime().exec("ping -c 3 www.google.com")
                    val returnVal = process.waitFor()
                    val reachable = (returnVal == 0)
                    if (!isInternetAvailable.equals(reachable)) {
                        isInternetAvailable = reachable
                        Helper.isInternetAvailable.postValue(reachable)
                    }
                }catch (ex:Exception){

                }

            }
        }, 0, 3000)
    }
}