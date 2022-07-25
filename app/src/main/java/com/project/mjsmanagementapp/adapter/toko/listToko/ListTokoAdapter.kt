package com.project.mjsmanagementapp.adapter.toko.listToko

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.model.toko.getListToko.ResponseListTokoItem
import kotlinx.android.synthetic.main.itemlisttoko.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ListTokoAdapter (val data: List<ResponseListTokoItem>?, private val click: onClickItem) : RecyclerView.Adapter<ListTokoAdapter.ViewHolder>() {

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
        fun onBind(get: ResponseListTokoItem?) {
            itemView.txtDomisiliToko.text = get?.tokoKecamatan
            itemView.txtNamaToko.text = get?.tokoNama
            itemView.txtPICToko.text = get?.tokoPicName
            itemView.txtNoPelanggan.text = get?.tokoNoPelanggan
        }
    }

    interface onClickItem {
        fun clicked(item: ResponseListTokoItem?)
    }

}