package com.example.wikucafe.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wikucafe.Data.dataMenu
import com.example.wikucafe.R

class MenuAdapter(private val menulist: ArrayList<dataMenu>) :
    RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {

    val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_card_makanan, parent, false)
    return MenuViewHolder(view)
}

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val currentMenu = menulist[position]

        Glide.with(holder.itemView.context)
            .load(currentMenu.img)
            .into(holder.img_menu)

        holder.tv_namamenu.text = currentMenu.menuName
        holder.tv_harga.text = currentMenu.hargaMenu.toString()
        holder.tv_desc.text = currentMenu.desc


    }

    override fun getItemCount(): Int {
       return menulist.size
    }


    class MenuViewHolder(view: View) :RecyclerView.ViewHolder(view){
        val img_menu = view.findViewById<ImageView>(R.id.img_menu)
        val tv_namamenu = view.findViewById<TextView>(R.id.tv_namaMenu)
        val tv_harga = view.findViewById<TextView>(R.id.tv_harga)
        val tv_desc = view.findViewById<TextView>(R.id.tv_desc)
    }
}
