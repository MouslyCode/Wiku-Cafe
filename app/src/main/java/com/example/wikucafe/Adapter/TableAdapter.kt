package com.example.wikucafe.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wikucafe.Data.DataTable
import com.example.wikucafe.R

class TableAdapter(private val tableList: ArrayList<DataTable>) :
    RecyclerView.Adapter<TableAdapter.TableViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_card_table, parent, false)
        return TableViewHolder(view)
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        val curretTable = tableList[position]
        holder.tv_meja.text = curretTable.nomorMeja.toString()
    }



    override fun getItemCount(): Int {
        return tableList.size
    }

    class TableViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv_meja = view.findViewById<TextView>(R.id.tv_Meja)
    }
}
