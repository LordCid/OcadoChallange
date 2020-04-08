package com.example.ocadochallenge.view

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    lateinit var beerListAdapter: ProductListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        beerListAdapter = ProductListAdapter(imagesLoader)
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
            this.adapter = beerListAdapter
        }
    }

    override fun showResultList(productList: List<ProductCluster>) {
        listView.visibility = VISIBLE
        error_tv.visibility = GONE
        beerListAdapter.clusterList = productList
    }

    override fun showError() {
        listView.visibility = GONE
        error_tv.visibility = VISIBLE
        error_tv.text = getText(R.string.error_message)
    }
}
