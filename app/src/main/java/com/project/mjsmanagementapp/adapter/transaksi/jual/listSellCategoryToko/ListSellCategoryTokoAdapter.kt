package com.project.mjsmanagementapp.adapter.transaksi.jual.listSellCategoryToko

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.model.transaksi.jual.getListTrcSellCategoryToko.ResponseListTrcSellCategoryTokoItem
import kotlinx.android.synthetic.main.itemlisttoko.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ListSellCategoryTokoAdapter(val data: List<ResponseListTrcSellCategoryTokoItem>?, val click: onClickItem):RecyclerView.Adapter<ListSellCategoryTokoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemlisttoko, parent, false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(data?.get(position))
        holder.itemView.onClick {
            click.clicked(data?.get(position))
        }
    }


    override fun getItemCount() = data?.size ?:0

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(get: ResponseListTrcSellCategoryTokoItem?){
            itemView.txtNamaToko.text = get?.trcSellCategoryTokoName
        }
    }
    interface onClickItem {
        fun clicked(item: ResponseListTrcSellCategoryTokoItem?)
    }
}