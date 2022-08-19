package com.project.mjsmanagementapp.adapter.produk.listSubProduk

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.produk.listSubProduk.ResultItem
import kotlinx.android.synthetic.main.itemlistsubproduk.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ListSubProdukAdapter(val data: List<ResultItem>?, private val click: ListSubProdukAdapter.onClickItem) : RecyclerView.Adapter<ListSubProdukAdapter.ViewHolder>(){


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListSubProdukAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemlistsubproduk, parent, false)
        return ListSubProdukAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListSubProdukAdapter.ViewHolder, position: Int) {
        holder.onBind(data?.get(position))

        holder.itemView.onClick {
            click.clicked(data?.get(position))
        }
    }

    override fun getItemCount(): Int = data?.size ?: 0

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        fun onBind(get : ResultItem?){
            itemView.txtKodeJenisProduk.text = get?.subProductCode
            Glide.with(itemView.context)
                .load(ApiClient.BASE_URL + get?.subProductPhoto)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .into(itemView.imgJenisProduk)
        }
    }

    interface onClickItem {
        fun clicked(item: ResultItem?)
    }

}
