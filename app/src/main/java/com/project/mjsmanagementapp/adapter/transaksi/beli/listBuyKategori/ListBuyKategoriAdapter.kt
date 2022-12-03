package com.project.mjsmanagementapp.adapter.transaksi.beli.listBuyKategori

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.model.transaksi.beli.getListTrcBuyKategori.ResponseListTrcBuyKategoriItem
import kotlinx.android.synthetic.main.itemlistpopembelian.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ListBuyKategoriAdapter(val data: List<ResponseListTrcBuyKategoriItem>?, val click: onClickItem ): RecyclerView.Adapter<ListBuyKategoriAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListBuyKategoriAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemlistpopembelian, parent, false)
        return ViewHolder(view)
        
    }

    override fun onBindViewHolder(holder: ListBuyKategoriAdapter.ViewHolder, position: Int) {
        holder.onBind(data?.get(position))
        holder.itemView.onClick {
            click.clicked(data?.get(position))
        }
    }

    override fun getItemCount() = data?.size ?:0


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(get: ResponseListTrcBuyKategoriItem?){
            itemView.txtNomorPoBeli.text = get?.trcBuyCategoryNota
            itemView.txtTanggalPoBeli.text = get?.trcBuyCategoryTanggal
            itemView.txtJenisPayment.text = get?.trcBuyCategoryPayment
            itemView.txtNamaPIC.text = get?.trcBuyCategoryPic

        }
    }

    interface onClickItem {
        fun clicked(item: ResponseListTrcBuyKategoriItem?)
    }

}