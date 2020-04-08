package com.example.ocadochallenge.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codewaves.stickyheadergrid.StickyHeaderGridAdapter
import com.example.ocadochallenge.R
import com.example.ocadochallenge.domain.imageloader.ImagesLoader
import com.example.ocadochallenge.domain.model.ProductCluster
import kotlinx.android.synthetic.main.header_list.view.*
import kotlinx.android.synthetic.main.item_list.view.*
import kotlin.properties.Delegates

class ProductListAdapter(private val imagesLoader: ImagesLoader) : StickyHeaderGridAdapter() {

    var clusterList: List<ProductCluster> by Delegates.observable(emptyList()) { _, oldValue, newValue ->
        if (oldValue != newValue) {
            notifyDataSetChanged()
        }
    }

    override fun getSectionCount(): Int {
        return clusterList.size
    }

    override fun getSectionItemCount(clusterIndex: Int): Int {
        return clusterList[clusterIndex].products.size
    }


    override fun onCreateHeaderViewHolder(parent: ViewGroup, headerType: Int): HeaderViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.header_list, parent, false)
        return ClusterHeaderViewHolder(view)
    }

    override fun onCreateItemViewHolder(parent: ViewGroup, itemType: Int): ItemViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_list, parent, false)
        return ListItemViewHolder(imagesLoader, view)
    }


    override fun onBindHeaderViewHolder(viewHolder: HeaderViewHolder, section: Int) {
        val headerViewHolder = viewHolder as ClusterHeaderViewHolder
        headerViewHolder.showClusterTag(clusterList[section].tag)
    }

    override fun onBindItemViewHolder(viewHolder: ItemViewHolder, section: Int, offset: Int) {
        val listItemHolder = viewHolder as ListItemViewHolder
        val productData = clusterList[section].products[offset]
        with(listItemHolder){
            showImage(productData.imageUrl)
            showTitle(productData.title)
            showPrice(productData.price)
            showSize(productData.size)
        }
    }

}

class ClusterHeaderViewHolder(itemView: View): StickyHeaderGridAdapter.HeaderViewHolder(itemView){
    fun showClusterTag(tag: String){
        itemView.cluster_tv.text = tag
    }
}

class ListItemViewHolder(
    private val imagesLoader: ImagesLoader,
    itemView: View
) : StickyHeaderGridAdapter.ItemViewHolder(itemView) {

    fun showImage(imageUrl: String) {
        imagesLoader.loadImage(imageUrl, itemView.food_image)
    }

    fun showTitle(title: String) {
        itemView.title_tv.text = title
    }

    fun showSize(size: String) {
        itemView.size_tv.text = size
    }

    fun showPrice(price: String) {
        itemView.price_tv.text = price
    }

}