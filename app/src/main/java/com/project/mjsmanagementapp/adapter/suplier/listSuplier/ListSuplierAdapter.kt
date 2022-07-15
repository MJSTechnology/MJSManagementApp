package com.project.mjsmanagementapp.adapter.suplier.listSuplier

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.mjsmanagementapp.R
import com.project.mjsmanagementapp.model.suplier.getListSuplier.ResponseListSuplierItem
import kotlinx.android.synthetic.main.itemlistsuplier.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ListSuplierAdapter(val data: List<ResponseListSuplierItem>?, private val click: onClickItem) : RecyclerView.Adapter<ListSuplierAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemlistsuplier, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(data?.get(position))
        holder.itemView.onClick {
            click.clicked(data?.get(position))
        }
    }

    override fun getItemCount() = data?.size ?: 0

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(get : ResponseListSuplierItem?){
            itemView.txtNamaSuplier.text = get?.supplierNama
            itemView.txtKabupatenSuplier.text = get?.supplierKabupaten
        }
    }

    interface onClickItem {
        fun clicked(item: ResponseListSuplierItem?)
    }


}