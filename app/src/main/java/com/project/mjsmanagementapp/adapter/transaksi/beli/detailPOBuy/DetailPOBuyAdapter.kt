package com.project.mjsmanagementapp.adapter.transaksi.beli.detailPOBuy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.model.transaksi.beli.getDetailPOBuy.ResultItem
import com.project.mjsmanagementapp.model.transaksi.beli.getListTrcBuyKategori.ResponseListTrcBuyKategoriItem
import kotlinx.android.synthetic.main.itemlistprodukpo.view.*

class DetailPOBuyAdapter (val data: List<ResultItem>, val click: OnClickItem) : RecyclerView.Adapter<DetailPOBuyAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailPOBuyAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemlistprodukpo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailPOBuyAdapter.ViewHolder, position: Int) {
        holder.onBind(data?.get(position))
    }

    override fun getItemCount() = data?.size ?:0

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun onBind(get: ResultItem){
            itemView.txtNamaProduk.text = get?.trcBuyItem
            itemView.txtKodeSubProduk.text = get?.trcBuyItemCode
            itemView.txtHargaProduk.text = get?.trcBuyItemHargaSatuan
            itemView.txtJumlahProduk.text = get?.trcBuyItemJumlah
            itemView.txtJenisSatuan.text = get?.trcBuyItemTipe

        }

    }

    interface OnClickItem {
        fun clicked(item: ResultItem?)
    }
}
