package com.sathish.examplelivedata.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sathish.examplelivedata.R
import com.sathish.examplelivedata.service.model.Blog
import com.sathish.examplelivedata.view.adapter.BlogAdapter
import com.sathish.examplelivedata.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
  lateinit  var viewModel:MainViewModel
    internal var mBlogDataset = ArrayList<Blog>()
    lateinit  internal  var mBlogAdapter: BlogAdapter
    private var getActivityContext: MainActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getActivityContext = this@MainActivity

         viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        initObserver()
        viewModel.callWebservice()



    }



    fun initObserver(){

        viewModel.allBlog.observe(this, Observer {

            var response = viewModel.allBlog.value
          val status =   response!!.status.toString()
            if(status.equals("ok")){
                txtView.setText(response!!.message)
                mBlogDataset = response.data as ArrayList<Blog>
                if (mBlogDataset.size>0)
                {
                       mBlogAdapter = BlogAdapter(getActivityContext!!,mBlogDataset)
                    rcyVw_search_blog_list.setLayoutManager( LinearLayoutManager(getActivityContext));
                        rcyVw_search_blog_list!!.adapter = mBlogAdapter
                    rcyVw_search_blog_list!!.adapter!!.notifyDataSetChanged()
                }

            }

        })

    }
}
