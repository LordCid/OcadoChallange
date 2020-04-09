package com.example.ocadochallenge.view

import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ocadochallenge.R
import com.example.ocadochallenge.domain.imageloader.ImagesLoader
import com.example.ocadochallenge.domain.model.ProductCluster
import com.example.ocadochallenge.presenter.ProductListContract
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.product_list_activity.*
import javax.inject.Inject

class ProductListActivity : AppCompatActivity(), ProductListContract.View {

    @Inject
    lateinit var presenter: ProductListContract.Presenter

    @Inject
    lateinit var imagesLoader: ImagesLoader

    lateinit var listAdapter: ProductListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        listAdapter = ProductListAdapter(imagesLoader)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_list_activity)
        setUpUI()
    }

    override fun onResume() {
        super.onResume()
        presenter.getProductList()
    }

    private fun setUpUI() {
        listView.apply {
            layoutManager = GridLayoutManager(context, 1)
            this.adapter = listAdapter
        }
        listAdapter.onClickItem = {
            Toast.makeText(this, "clicked id=$it", Toast.LENGTH_SHORT).show()
        }
    }

    override fun showResultList(productList: List<ProductCluster>) {
        listView.visibility = VISIBLE
        error_tv.visibility = GONE
        listAdapter.clusterList = productList
    }

    override fun showError() {
        listView.visibility = GONE
        error_tv.visibility = VISIBLE
        error_tv.text = getText(R.string.error_message)
    }
}
