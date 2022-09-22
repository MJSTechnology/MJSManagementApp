package com.project.mjsmanagementapp.adapter.transaksi.beli.listBuyCategory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.adapter.toko.listToko.ListTokoAdapter
import com.project.mjsmanagementapp.model.toko.getListToko.ResponseListTokoItem
import com.project.mjsmanagementapp.model.transaksi.beli.getListTrcBuyCategory.ResponseListTrcBuyCategoryItem
import kotlinx.android.synthetic.main.itemlistpopembelian.view.*
import kotlinx.android.synthetic.main.itemlistsuplier.view.*
import kotlinx.android.synthetic.main.itemlisttoko.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ListBuyCategoryAdapter(val data: List<ResponseListTrcBuyCategoryItem>?, val click: onClickItem): RecyclerView.Adapter<ListBuyCategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemlistpopembelian, parent, false)
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
        fun onBind(get: ResponseListTrcBuyCategoryItem?) {
            itemView.txtTanggalPoBeli.text = get?.trcBuyCategoryTanggal
            itemView.txtNomorPoBeli.text = get?.trcBuyCategoryNota
            itemView.txtNamaPic.text = get?.trcBuyCategoryPic
        }
    }


    interface onClickItem {
        fun clicked(item: ResponseListTrcBuyCategoryItem?)
    }
}