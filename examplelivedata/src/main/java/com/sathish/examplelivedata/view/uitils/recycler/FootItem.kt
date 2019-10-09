package com.sathish.myapplication.utils.recycler

import android.view.View
import android.view.ViewGroup

abstract class FootItem {

    var loadingText: CharSequence? = null
    var endText: CharSequence? = null
    var pullToLoadText: CharSequence? = null

    abstract fun onCreateView(parent: ViewGroup): View

    abstract fun onBindData(view: View, state: Int)

}
