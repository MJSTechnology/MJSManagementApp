package com.project.mjsmanagementapp.adapter.transaksi.beli.listBuyCategorySuplier

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.model.transaksi.beli.getListTrcBuyCategorySuplier.ResponseListTrcBuyCategorySuplierItem
import kotlinx.android.synthetic.main.itemlistsuplierpembelian.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ListBuyCategorySuplierAdapter(val data: List<ResponseListTrcBuyCategorySuplierItem>?, val click: onClickItem): RecyclerView.Adapter<ListBuyCategorySuplierAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListBuyCategorySuplierAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemlistsuplierpembelian, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListBuyCategorySuplierAdapter.ViewHolder, position: Int) {
        holder.onBind(data?.get(position))
        holder.itemView.onClick {
            click.clicked(data?.get(position))
        }
    }

    override fun getItemCount() = data?.size ?:0

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        fun onBind(get: ResponseListTrcBuyCategorySuplierItem?){
            itemView.txtNamaSuplier.text = get?.trcBuyCategorySupplierName
        }
    }

    interface onClickItem {
        fun clicked(item: ResponseListTrcBuyCategorySuplierItem?)
    }


}