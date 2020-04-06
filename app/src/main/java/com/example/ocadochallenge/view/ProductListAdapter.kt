package com.example.ocadochallenge.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ocadochallenge.R
import com.example.ocadochallenge.domain.imageloader.ImagesLoader
import com.example.ocadochallenge.domain.model.ProductModel
import kotlinx.android.synthetic.main.item_list.view.*
import kotlin.properties.Delegates

class ProductListAdapter(private val imagesLoader: ImagesLoader) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var productList: List<ProductModel> by Delegates.observable(emptyList()) { _, oldValue, newValue ->
        if (oldValue != newValue) {
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_list, parent, false)
        return ListItemViewHolder(imagesLoader, view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val listItemHolder = holder as ListItemViewHolder
        val beerData = productList.get(position)
        listItemHolder.showImage(beerData.image)
        listItemHolder.showName(beerData.name)
        listItemHolder.showTagline(beerData.tagline)
        listItemHolder.showDescription(beerData.description)
        listItemHolder.showABV(beerData.abv)
    }
}

class ListItemViewHolder(
    private val imagesLoader: ImagesLoader,
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun showImage(imageUrl: String) {
        imagesLoader.loadImage(imageUrl, itemView.food_image)
    }

    fun showName(name: String) {
        itemView.name_tv.text = name
    }

    fun showTagline(tagline: String) {
        itemView.tagline_tv.text = tagline
    }

    fun showDescription(description: String) {
        itemView.description_tv.text = description
    }

    fun showABV(abv: Float) {
        itemView.abv_tv.text = abv.toString()
    }
}