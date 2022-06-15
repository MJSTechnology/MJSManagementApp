package com.project.mjsmanagementapp.adapter.ListToko

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.model.getListToko.ResponseListToko
import com.project.mjsmanagementapp.model.getListToko.ResponseListTokoItem
import kotlinx.android.synthetic.main.itemlisttoko.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ListTokoAdapter(val data: List<ResponseListTokoItem>, private val click: onClickItem) : RecyclerView.Adapter<ListTokoAdapter.ViewHolder>() {

    interface onClickItem {
        fun clicked(item: ResponseListTokoItem?)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)  {
        fun onBind(get: ResponseListTokoItem?){
            itemView.txtDomisiliToko.text = get?.tokoWilayah
            itemView.txtNamaToko.text = get?.tokoNama
            itemView.txtPICToko.text = get?.tokoPicName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListTokoAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemlisttoko, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListTokoAdapter.ViewHolder, position: Int) {
        holder.onBind(data?.get(position))
        holder.itemView.onClick {
            click.clicked(data?.get(position))
        }
    }

    override fun getItemCount() = data?.size ?:0
}