package com.example.ocadochallenge.view

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brewdogbeers.Constants.ARG_PRODUCT_ID
import com.example.ocadochallenge.R
import com.example.ocadochallenge.domain.imageloader.ImagesLoader
import com.example.ocadochallenge.domain.model.ProductCluster
import com.example.ocadochallenge.presenter.ProductListContract
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.product_list_activity.*
import javax.inject.Inject

class ProductListActivity: AppCompatActivity(), ProductListContract.View {

    @Inject
    lateinit var presenter: ProductListContract.Presenter

    @Inject
    lateinit var imagesLoader: ImagesLoader

    private lateinit var listAdapter: ProductListAdapter


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
            addItemDecoration(
                MarginItemDecoration(
                    resources.getDimension(R.dimen.margin_padding_size_small).toInt()
                )
            )
            this.adapter = listAdapter
        }
        listAdapter.onClickItem = {
            val intent = Intent(this, ProductDetailActivity::class.java)
            intent.putExtra(ARG_PRODUCT_ID, it)
            startActivity(intent)
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

    class MarginItemDecoration(private val spaceHeight: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View,
                                    parent: RecyclerView, state: RecyclerView.State) {
            with(outRect) {
                if (parent.getChildAdapterPosition(view) == 0) {
                    top = spaceHeight
                }
                left =  spaceHeight
                right = spaceHeight
                bottom = spaceHeight
            }
        }
    }
}
