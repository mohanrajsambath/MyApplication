package com.sathish.myapplication.utils.recycler

import android.view.View
import android.view.ViewGroup

abstract class EmptyItem {

    internal var mEmptyText: CharSequence? = null
    internal var mEmptyIconRes = -1

    internal abstract fun onCreateView(parent: ViewGroup): View

    internal abstract fun onBindData(view: View)

}
