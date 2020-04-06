package com.example.ocadochallenge.view

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ocadochallenge.R
import com.example.ocadochallenge.domain.imageloader.ImagesLoader
import com.example.ocadochallenge.domain.model.ProductModel
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

    private var state = ProductListViewState.SORT_INCREASE_ABV

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        beerListAdapter = ProductListAdapter(imagesLoader)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_list_activity)
        setUpUI()
    }

    private fun setUpUI() {
        listView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            this.adapter = beerListAdapter
        }
        sort_increaseabv_button.setOnClickListener {
            sort_state_tv.text = getText(R.string.increase_state)
            state = ProductListViewState.SORT_INCREASE_ABV
        }
        sort_decreaseabv_button.setOnClickListener {
            sort_state_tv.text = getText(R.string.decrease_state)
            state = ProductListViewState.SORT_DECREASE_ABV
        }

        toolbar_search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                when (state) {
                    ProductListViewState.SORT_INCREASE_ABV -> presenter.getBeerListSortByIncreasingABV(query)
                    ProductListViewState.SORT_DECREASE_ABV -> presenter.getBeerListSortByDecreasingABV(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
    }

    override fun showResultList(productList: List<ProductModel>) {
        listView.visibility = VISIBLE
        error_tv.visibility = GONE
        beerListAdapter.productList = productList
    }

    override fun showError() {
        listView.visibility = GONE
        error_tv.visibility = VISIBLE
        error_tv.text = getText(R.string.error_message)
    }
}

enum class ProductListViewState { SORT_INCREASE_ABV, SORT_DECREASE_ABV }
