package com.example.ocadochallenge.view

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.example.brewdogbeers.Constants.ARG_PRODUCT_ID
import com.example.ocadochallenge.R
import com.example.ocadochallenge.domain.imageloader.ImagesLoader
import com.example.ocadochallenge.domain.model.Product
import com.example.ocadochallenge.presenter.ProductDetailContract
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.product_detail_activity.*
import javax.inject.Inject

class ProductDetailActivity: AppCompatActivity(), ProductDetailContract.View {

    @Inject
    lateinit var presenter: ProductDetailContract.Presenter

    @Inject
    lateinit var imagesLoader: ImagesLoader

    private val id: Int by lazy { intent?.extras?.getInt(ARG_PRODUCT_ID) ?:0 }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_detail_activity)
        presenter.getProduct(id)
    }

    override fun showProduct(product: Product) {
        imagesLoader.loadImage(product.imageUrl, product_detail_image)
        title_tv.text = product.title
        description_tv.text = product.description
        allergy_tv.text = product.allergyInformation
    }

    override fun showError() {
        content_container.visibility = GONE
        error_tv.visibility = VISIBLE
    }
}