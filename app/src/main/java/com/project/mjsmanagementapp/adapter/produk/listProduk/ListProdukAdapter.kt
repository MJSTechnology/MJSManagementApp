package com.project.mjsmanagementapp.adapter.produk.listProduk

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.adapter.toko.listToko.ListTokoAdapter
import com.project.mjsmanagementapp.data.ApiClient
import com.project.mjsmanagementapp.model.produk.listProduk.ResponseListProdukItem
import com.project.mjsmanagementapp.model.toko.getListToko.ResponseListTokoItem
import kotlinx.android.synthetic.main.itemlistproduk.view.*
import kotlinx.android.synthetic.main.itemlistsuplier.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ListProdukAdapter(val data:List<ResponseListProdukItem>?, private val click: ListProdukAdapter.onClickItem) : RecyclerView.Adapter<ListProdukAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListProdukAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemlistproduk, parent, false)
        return ListProdukAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListProdukAdapter.ViewHolder, position: Int) {
        holder.onBind(data?.get(position))

        holder.itemView.onClick {
            click.clicked(data?.get(position))
        }
    }

    override fun getItemCount(): Int = data?.size ?: 0

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun onBind(get : ResponseListProdukItem?){
            itemView.txtNamaProduk.text = get?.productName
            Glide.with(itemView.context)
                .load(ApiClient.BASE_URL + get?.productPhoto)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .into(itemView.imgProduk)
        }
    }

    interface onClickItem {
        fun clicked(item: ResponseListProdukItem?)
    }
}