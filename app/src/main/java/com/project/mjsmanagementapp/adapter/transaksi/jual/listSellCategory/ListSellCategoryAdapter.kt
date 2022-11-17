package com.project.mjsmanagementapp.adapter.transaksi.jual.listSellCategory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.model.transaksi.jual.getListSellCategory.ResultItem
import kotlinx.android.synthetic.main.itemlistpopenjualan.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ListSellCategoryAdapter (val data: List<ResultItem>?, val click: ListSellCategoryAdapter.onClickItem):
    RecyclerView.Adapter<ListSellCategoryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemlistpopenjualan, parent, false)

        return ListSellCategoryAdapter.ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(data?.get(position))
        holder.itemView.onClick {
            click.clicked(data?.get(position))
        }
    }

    override fun getItemCount() = data?.size ?:0


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun onBind(get: ResultItem?) {
            itemView.txtTanggalPoJual.text = get?.trcSellCategoryTanggal
            itemView.txtNomorPoJual.text = get?.trcSellCategoryNota
            itemView.txtNamaPemilikToko.text = get?.trcSellCategoryPic
            itemView.txtJenisPayment.text = get?.trcSellCategoryPayment

        }
    }

    interface onClickItem {
        fun clicked(item: ResultItem?)
    }

}