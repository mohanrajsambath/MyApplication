package com.task.nebenan.view.utils.recycler
/*
 * Copyright (c) 2019. Created f
 *
 * File Name : DefaultFootItem.kt.
 * ClassName : DefaultFootItem.
 * @author : Sathish
 *
 */



import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.sathish.examplelivedata.R
import com.sathish.myapplication.utils.recycler.EmptyItem



class DefaultEmptyItem : EmptyItem() {

    private var mEmptyTextView: TextView? = null
    private var mEmptyImageView: ImageView? = null

    override fun onCreateView(parent: ViewGroup): View {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_with_footer_empty_layout, parent, false)
        view.setLayoutParams(ViewGroup.LayoutParams(parent.measuredWidth, parent.measuredHeight))
        mEmptyTextView = view.findViewById(R.id.rv_with_footer_empty_title)
        mEmptyImageView = view.findViewById(R.id.rv_with_footer_empty_icon)
        return view
    }

    override fun onBindData(view: View) {
        if (TextUtils.isEmpty(mEmptyText)) {
            mEmptyTextView!!.visibility = View.GONE
        } else {
            mEmptyTextView!!.visibility = View.VISIBLE
            mEmptyTextView!!.setText(mEmptyText)
        }

        if (mEmptyIconRes !== -1) {
            mEmptyImageView!!.setImageResource(mEmptyIconRes)
        }
    }
}
