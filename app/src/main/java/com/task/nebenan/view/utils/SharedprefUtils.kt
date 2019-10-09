package com.task.nebenan.view.utils

import android.content.Context
import android.content.SharedPreferences
import java.util.*

object SharedprefUtils {


    /*
     * Save the file name in the phone inside
     */
    private val FILE_NAME = "Credentials_Preferences"

    /*
     * Method for saving data, we need to get the specific type of data stored, and then call different methods depending on the type of preservation
     *
     * @param context
     * @param key
     * @param object
     */
    fun setParam(context: Context, key: String, `object`: Any) {

        val type = `object`.javaClass.simpleName
        var sp: SharedPreferences? = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        var editor: SharedPreferences.Editor? = sp!!.edit()

        if ("String" == type) {
            editor!!.putString(key, `object` as String)
        } else if ("Integer" == type) {
            editor!!.putInt(key, `object` as Int)
        } else if ("Boolean" == type) {
            editor!!.putBoolean(key, `object` as Boolean)
        } else if ("Float" == type) {
            editor!!.putFloat(type, `object` as Float)
        } else if ("Long" == type) {
            editor!!.putLong(type, `object` as Long)
        }
        editor!!.apply()
        sp = null
        editor = null
    }


    /*
     * Get method for saving data, we should be saved by default to a specific type of data, then the method call with respect to the acquisition value
     *
     * @param context
     * @param key
     * @param defaultObject
     * @return
     */
    fun getParam(context: Context, key: String, defaultObject: Any): Any? {
        val type = defaultObject.javaClass.simpleName
        if (context != null) {
            var sp: SharedPreferences? = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
            var returnObj: Any? = null
            if (sp != null) {
                if ("String" == type) {
                    returnObj = sp.getString(key, defaultObject as String)
                } else if ("Integer" == type) {
                    returnObj = sp.getInt(key, defaultObject as Int)
                } else if ("Boolean" == type) {
                    returnObj = sp.getBoolean(key, defaultObject as Boolean)
                } else if ("Float" == type) {
                    returnObj = sp.getFloat(type, defaultObject as Float)
                } else if ("Long" == type) {
                    returnObj = sp.getLong(type, defaultObject as Long)
                }
                sp = null
                return returnObj
            } else {
                return null
            }
        } else {
            return null
        }
    }




}