package com.task.nebenan.view.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object NetworksUtils
{


    /**
     * Analyzing the network connection is open, including mobile data connection
     *
     * @param context Context
     * @return Are Networking
     */
    fun isNetworkAvailable(context: Context): Boolean {
        var netstate = false
        val connectivity = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity != null) {

            val info = connectivity.allNetworkInfo
            if (info != null) {
                for (i in info.indices) {

                    if (info[i].state == NetworkInfo.State.CONNECTED) {

                        netstate = true
                        break
                    }
                }
            }
        }
        return netstate
    }



}