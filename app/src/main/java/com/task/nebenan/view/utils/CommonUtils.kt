package com.task.nebenan.view.utils

import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AlertDialog
import android.view.inputmethod.InputMethodManager
import com.task.nebenan.R
import java.util.*

object CommonUtils {

    var hasNetworkAlertShown: Boolean = false


    fun netConnectivityFailed(mActivity: Context) {

        if (!hasNetworkAlertShown) {
            hasNetworkAlertShown = true
            var alertDialog: AlertDialog.Builder = AlertDialog.Builder(mActivity, R.style.DialogTheme)
            try {
                alertDialog.setTitle(R.string.tittle_network_error)
                alertDialog.setCancelable(false)
                alertDialog.setMessage(R.string.msg_error_state)
                var dialog: AlertDialog? = alertDialog.setPositiveButton("OK", { dialog1, which ->
                    hasNetworkAlertShown = false
                    dialog1.dismiss()
                }).create()
                dialog!!.setCanceledOnTouchOutside(false)
                dialog.setOnShowListener { dialogInterface ->
                    val positive = (dialogInterface as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        positive.setTextColor(mActivity.getColor(R.color.dialog_text_color))
                    }
                    val negative = dialogInterface.getButton(AlertDialog.BUTTON_NEGATIVE)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        negative.setTextColor(mActivity.getColor(R.color.dialog_text_color))
                    }
                }
                dialog.show()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }


    /*
     * Hides the soft keyboard
     */
    fun hideSoftKeyboard(activity: Activity) {
        val focusedView = activity.currentFocus
        if (focusedView != null) {
            val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            Objects.requireNonNull(inputMethodManager).hideSoftInputFromWindow(focusedView.windowToken, 0)
        }
    }


}