package com.task.nebenan.view.utils.recycler


/*
 * Coimbatore,TamilNadu,India.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.IntDef
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.sathish.myapplication.utils.recycler.DefaultFootItem
import com.sathish.myapplication.utils.recycler.EmptyItem
import com.sathish.myapplication.utils.recycler.FootItem
import com.task.nebenan.service.model.TemplateModel
import com.task.nebenan.view.utils.AppLog


import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * A [RecyclerView] with footer which enables load more.
 *
 * @author Vgs-User
 */
class RecyclerViewWithFooter : androidx.recyclerview.widget.RecyclerView {

    private var mIsGetDataForNet = false
    private var mTemplateList: List<TemplateModel>? = null

    @State
    private var mState = STATE_NONE

    /**
     * default FootItem;
     */
    private var mFootItem: FootItem = DefaultFootItem()
    private var mEmptyItem: EmptyItem = DefaultEmptyItem()

    private val mAdapterDataObserver = object : androidx.recyclerview.widget.RecyclerView.AdapterDataObserver() {

        override fun onChanged() {
            super.onChanged()
            reset()
        }

        private fun reset() {
            mIsGetDataForNet = false
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
            super.onItemRangeChanged(positionStart, itemCount)
            reset()

        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
            super.onItemRangeChanged(positionStart, itemCount, payload)
            reset()

        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount)
            reset()

        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            super.onItemRangeRemoved(positionStart, itemCount)
            reset()
        }

        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
            super.onItemRangeMoved(fromPosition, toPosition, itemCount)
            reset()
        }
    }

    /**
     * The data is empty
     */
    private val isEmpty: Boolean
        get() = mState == STATE_NONE && adapter!!.itemCount == 0 || mState != STATE_NONE && adapter!!.itemCount == 1

    val isLoadMoreEnable: Boolean
        get() = mState != STATE_LOADING


    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    private fun init() {
        setVerticalLinearLayout()
    }

    fun setVerticalLinearLayout() {
        RecyclerViewUtils.setVerticalLinearLayout(this)
    }

    fun setGridLayout(span: Int) {
        RecyclerViewUtils.setGridLayout(this, span)
    }

    fun setStaggeredGridLayoutManager(spanCount: Int) {
        RecyclerViewUtils.setStaggeredGridLayoutManager(this, spanCount)
    }

    fun setOnLoadMoreListener(onLoadMoreListener: OnLoadMoreListener) {
        mState = STATE_PULL_TO_LOAD

        val wrapper = OnLoadMoreListenerWrapper(onLoadMoreListener)

        addOnScrollListener(object : androidx.recyclerview.widget.RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: androidx.recyclerview.widget.RecyclerView, newState: Int) {

                try {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE) {
                        val layoutManager = recyclerView.layoutManager
                        if (layoutManager is androidx.recyclerview.widget.LinearLayoutManager) {
                            val lastVisiblePosition = layoutManager.findLastVisibleItemPosition()
                            if (lastVisiblePosition >= recyclerView.adapter!!.itemCount - 1) {
                                if (mState == STATE_PULL_TO_LOAD) {
                                    setLoading()
                                }
                                wrapper.onLoadMore()
                            }
                        } else if (layoutManager is androidx.recyclerview.widget.StaggeredGridLayoutManager) {
                            val staggeredGridLayoutManager = layoutManager as androidx.recyclerview.widget.StaggeredGridLayoutManager?
                            val last = IntArray(staggeredGridLayoutManager!!.spanCount)
                            staggeredGridLayoutManager.findLastVisibleItemPositions(last)

                            for (aLast in last) {
                                AppLog.i(TAG, aLast.toString() + "    " + recyclerView.adapter!!.itemCount)
                                if (aLast >= recyclerView.adapter!!.itemCount - 1) {
                                    if (mState == STATE_PULL_TO_LOAD) {
                                        setLoading()
                                    }
                                    wrapper.onLoadMore()
                                }
                            }
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        })
    }

    override fun setAdapter(adapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>?) {
        val loadMoreAdapter: LoadMoreAdapter
        if (adapter is LoadMoreAdapter) {
            loadMoreAdapter = adapter
            loadMoreAdapter.registerAdapterDataObserver(mAdapterDataObserver)
            super.setAdapter(adapter)
        } else {
            loadMoreAdapter = LoadMoreAdapter(adapter!!)
            loadMoreAdapter.registerAdapterDataObserver(mAdapterDataObserver)
            super.setAdapter(loadMoreAdapter)
        }
    }

    /**
     * Sets the loading hint string
     *
     * @param loadText Prompt string
     * @return [RecyclerViewWithFooter]
     */
    fun applyLoadingText(loadText: CharSequence): RecyclerViewWithFooter {
        mFootItem.loadingText = loadText
        return this
    }

    fun applyPullToLoadText(pullToLoadText: CharSequence): RecyclerViewWithFooter {
        mFootItem.pullToLoadText = pullToLoadText
        return this
    }

    fun applyEndText(endText: CharSequence): RecyclerViewWithFooter {
        mFootItem.endText = endText
        return this
    }

    fun applyEmptyText(emptyText: CharSequence, @DrawableRes drawableId: Int): RecyclerViewWithFooter {
        mEmptyItem.mEmptyIconRes = drawableId
        mEmptyItem.mEmptyText = emptyText
        return this
    }

    fun setFootItem(footItem: FootItem) {
        if (mFootItem != null) {
            if (footItem.endText == null) {
                footItem.endText = mFootItem.endText
            }
            if (footItem.loadingText == null) {
                footItem.loadingText = mFootItem.loadingText
            }
            if (footItem.pullToLoadText == null) {
                footItem.pullToLoadText = mFootItem.pullToLoadText
            }
        }
        mFootItem = footItem
    }

    fun setEmptyItem(emptyItem: EmptyItem) {
        if (mEmptyItem != null) {
            if (emptyItem.mEmptyIconRes === -1) {
                emptyItem.mEmptyIconRes = mEmptyItem.mEmptyIconRes
            }
            if (emptyItem.mEmptyText == null) {
                emptyItem.mEmptyText = mEmptyItem.mEmptyText
            }
        }
        mEmptyItem = emptyItem
    }

    /**
     * Switch to the loading state
     */
    fun setLoading() {
        if (adapter != null) {
            mState = STATE_LOADING
            mIsGetDataForNet = false
            adapter!!.notifyItemChanged(adapter!!.itemCount - 1)
        }
    }

    /**
     * Switch to no more data states
     *
     * @param end Prompt string
     */
    fun setEnd(end: CharSequence) {
        if (adapter != null) {
            mIsGetDataForNet = false
            mState = STATE_END
            mFootItem.endText = end
            adapter!!.notifyItemChanged(adapter!!.itemCount - 1)
        }
    }

    /**
     * Switch to no more data states
     */
    fun setEnd() {
        if (adapter != null) {
            mIsGetDataForNet = false
            mState = STATE_END
            adapter!!.notifyItemChanged(adapter!!.itemCount - 1)
        }
    }

    /**
     * Switch to no data state
     *
     * @param empty No data status message
     * @param resId No data status prompt icon
     */
    fun setEmpty(empty: CharSequence, @DrawableRes resId: Int) {
        if (adapter != null) {
            mState = STATE_EMPTY
            mEmptyItem.mEmptyText = empty
            mEmptyItem.mEmptyIconRes = resId
            if (isEmpty) {
                adapter!!.notifyDataSetChanged()
            }
        }
    }

    /**
     * Switch to no data state
     */
    fun setEmpty() {
        if (adapter != null) {
            mState = STATE_EMPTY
            if (isEmpty) {
                adapter!!.notifyDataSetChanged()
            }
        }
    }

    fun update(templateList: List<TemplateModel>) {
        mTemplateList = templateList
    }


    @IntDef(STATE_END, STATE_LOADING, STATE_EMPTY, STATE_NONE, STATE_PULL_TO_LOAD)
    @Retention(RetentionPolicy.SOURCE)
    annotation class State

    private inner class OnLoadMoreListenerWrapper(private val mOnLoadMoreListener: OnLoadMoreListener) :
        OnLoadMoreListener {

        override fun onLoadMore() {
            if (!mIsGetDataForNet && !isLoadMoreEnable) {
                mIsGetDataForNet = true
                mOnLoadMoreListener.onLoadMore()
            }
        }
    }

    inner class LoadMoreAdapter(var mAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>) :
        androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
            if (viewType == LOAD_MORE_VIEW_TYPE) {
                return LoadMoreVH()
            } else if (viewType == EMPTY_VIEW_TYPE) {
                return EmptyVH()
            }
            return mAdapter.onCreateViewHolder(parent, viewType)
        }

        override fun registerAdapterDataObserver(observer: androidx.recyclerview.widget.RecyclerView.AdapterDataObserver) {
            super.registerAdapterDataObserver(observer)
            mAdapter.registerAdapterDataObserver(observer)

        }

        override fun unregisterAdapterDataObserver(observer: androidx.recyclerview.widget.RecyclerView.AdapterDataObserver) {
            super.unregisterAdapterDataObserver(observer)
            mAdapter.unregisterAdapterDataObserver(observer)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            if (!isFootView(position)) {
                mAdapter.onBindViewHolder(holder as Nothing, position)
            } else {
                if (layoutManager is androidx.recyclerview.widget.StaggeredGridLayoutManager) {
                    val layoutParams = holder.itemView.layoutParams
                    if (layoutParams is androidx.recyclerview.widget.StaggeredGridLayoutManager.LayoutParams) {
                        layoutParams.isFullSpan = true
                    }
                } else if (layoutManager is androidx.recyclerview.widget.GridLayoutManager) {
                    val layoutManager = layoutManager as androidx.recyclerview.widget.GridLayoutManager?
                    layoutManager!!.spanSizeLookup = object : androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup() {
                        override fun getSpanSize(position: Int): Int {
                            val viewType = adapter!!.getItemViewType(position)
                            return if (viewType < 0) {
                                layoutManager.spanCount
                            } else 1
                        }
                    }
                }
                if (holder is VH) {
                    holder.onBindViewHolder()
                }
            }
        }

        private fun isFootView(position: Int): Boolean {
            return position == itemCount - 1 && mState != STATE_NONE
        }

        override fun getItemViewType(position: Int): Int {
            return if (!isFootView(position)) {
                mAdapter.getItemViewType(position)
            } else {
                if (mState == STATE_EMPTY && itemCount == 1) {
                    EMPTY_VIEW_TYPE
                } else {
                    LOAD_MORE_VIEW_TYPE
                }
            }
        }

        override fun getItemCount(): Int {
            return if (mState == STATE_NONE) {
                mAdapter.itemCount
            } else {
                mAdapter.itemCount + 1
            }
        }

        /**
         * Load more ViewHolder
         */
        private inner class LoadMoreVH : VH(mFootItem.onCreateView(this@RecyclerViewWithFooter)) {

            private val mItemView: View

            init {
                mItemView = itemView
            }

            override fun onBindViewHolder() {
                super.onBindViewHolder()
                if (mState == STATE_LOADING || mState == STATE_END || mState == STATE_PULL_TO_LOAD) {
                    mFootItem.onBindData(mItemView, mState)
                }
            }
        }

        /**
         * ViewHolder when the data is empty
         */
        private inner class EmptyVH : VH(mEmptyItem.onCreateView(this@RecyclerViewWithFooter)) {

            override fun onBindViewHolder() {
                super.onBindViewHolder()
                mEmptyItem.onBindData(itemView)
            }
        }

        internal open inner class VH(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

            open fun onBindViewHolder() {

            }
        }


    }

    companion object {

        private val TAG = "RecyclerViewWithFooter"

        const val STATE_END = 0
        const val STATE_LOADING = 1
        const val STATE_EMPTY = 2
        const val STATE_NONE = 3
        const val STATE_PULL_TO_LOAD = 4

        const val LOAD_MORE_VIEW_TYPE = -404
        const val EMPTY_VIEW_TYPE = -403
    }


}
