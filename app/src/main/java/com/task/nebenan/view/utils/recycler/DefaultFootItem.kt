package com.sathish.myapplication.utils.recycler

/*
 * Copyright (c) 2019. Created f
 *
 * File Name : DefaultFootItem.java.
 * ClassName : DefaultFootItem.
 *
 *
 */

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.sathish.myapplication.customview.TextViewFont
import com.task.nebenan.R
import com.task.nebenan.view.utils.recycler.RecyclerViewWithFooter


class DefaultFootItem : FootItem() {

    private var mProgressBar: ProgressBar? = null
    private var mLoadingText: TextViewFont? = null
    private var mEndTextView: TextViewFont? = null

    override fun onCreateView(parent: ViewGroup): View {
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.rv_with_footer_loading, parent, false)
        mProgressBar = view.findViewById(R.id.rv_with_footer_loading_progress)
        mEndTextView = view.findViewById(R.id.rv_with_footer_loading_end)
        mLoadingText = view.findViewById(R.id.rv_with_footer_loading_load)
        return view
    }

    override fun onBindData(view: View, state: Int) {
        if (state == RecyclerViewWithFooter.STATE_LOADING) {
            if (TextUtils.isEmpty(loadingText)) {
                showProgressBar(view.context.resources.getString(R.string.loading))
            } else {
                showProgressBar(loadingText!!)
            }
        } else if (state == RecyclerViewWithFooter.STATE_END) {
            showEnd(endText!!)
        } else if (state == RecyclerViewWithFooter.STATE_PULL_TO_LOAD) {
            if (TextUtils.isEmpty(pullToLoadText)) {
                showPullToLoad(view.context.resources.getString(R.string.rv_with_footer_pull_load_more))
            } else {
                showPullToLoad(loadingText!!)
            }
        }
    }

    fun showPullToLoad(message: CharSequence) {
        showEnd(message)
    }

    fun showProgressBar(load: CharSequence) {
        mEndTextView!!.setVisibility(View.GONE)
        mProgressBar!!.visibility = View.VISIBLE
        if (!TextUtils.isEmpty(load)) {
            mLoadingText!!.setVisibility(View.VISIBLE)
            mLoadingText!!.setText(load)
        } else {
            mLoadingText!!.setVisibility(View.GONE)
        }
    }

    fun showEnd(end: CharSequence) {
        mEndTextView!!.setVisibility(View.GONE)
        //        mEndTextView.setVisibility(View.VISIBLE);
        mProgressBar!!.visibility = View.GONE
        mLoadingText!!.setVisibility(View.GONE)
        if (!TextUtils.isEmpty(end)) {
            mEndTextView!!.setText(end)
        }
    }
}
