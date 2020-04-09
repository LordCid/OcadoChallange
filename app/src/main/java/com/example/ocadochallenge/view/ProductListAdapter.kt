package com.example.ocadochallenge.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codewaves.stickyheadergrid.StickyHeaderGridAdapter
import com.example.ocadochallenge.R
import com.example.ocadochallenge.domain.imageloader.ImagesLoader
import com.example.ocadochallenge.domain.model.Product
import com.example.ocadochallenge.domain.model.ProductCluster
import kotlinx.android.synthetic.main.header_list.view.*
import kotlinx.android.synthetic.main.item_list.view.*
import kotlin.properties.Delegates

class ProductListAdapter(private val imagesLoader: ImagesLoader) : StickyHeaderGridAdapter() {

    var clusterList: List<ProductCluster> by Delegates.observable(emptyList()) { _, oldValue, newValue ->
        if (oldValue != newValue) {
            notifyAllSectionsDataSetChanged()
        }
    }

    var onClickItem: (Int) -> Unit = {}

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
        return ListItemViewHolder(view, imagesLoader, onClickItem)
    }


    override fun onBindHeaderViewHolder(viewHolder: HeaderViewHolder, section: Int) {
        val headerViewHolder = viewHolder as ClusterHeaderViewHolder
        headerViewHolder.showClusterTag(clusterList[section].tag)
    }

    override fun onBindItemViewHolder(viewHolder: ItemViewHolder, section: Int, offset: Int) {
        val listItemHolder = viewHolder as ListItemViewHolder
        listItemHolder.bind(clusterList[section].products[offset])
    }

}

class ClusterHeaderViewHolder(itemView: View): StickyHeaderGridAdapter.HeaderViewHolder(itemView){
    fun showClusterTag(tag: String){
        itemView.cluster_tv.text = tag
    }
}

class ListItemViewHolder(
    itemView: View,
    private val imagesLoader: ImagesLoader,
    private val onClick: (Int) -> Unit
) : StickyHeaderGridAdapter.ItemViewHolder(itemView) {

    fun bind(product: Product){
        with(itemView){
            setOnClickListener { onClick(product.id) }
            imagesLoader.loadImage(product.imageUrl, food_image)
            title_tv.text = product.title
            size_tv.text = product.size
            price_tv.text = product.price
        }
    }

}