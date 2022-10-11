package com.example.hotel.view


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.hotel.R
import com.example.hotel.data.MenuItem


class MenuListAdapter:RecyclerView.Adapter<MenuListAdapter.ViewHolder>(){

    var data = listOf<MenuItem>()
        set(value) {
            field=value
            notifyDataSetChanged()
        }

    class ViewHolder( rootView:View):RecyclerView.ViewHolder(rootView) {
       private val itemNameTV:TextView= rootView.findViewById(R.id.textName)
       private  val itemPriceTv:TextView= rootView.findViewById(R.id.textPrice)

        fun bind(menuItem: MenuItem){
            itemNameTV.text = menuItem.itemName
            itemPriceTv.text = menuItem.itemPrice.toString()
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view =
           LayoutInflater.from(parent.context).inflate(R.layout.card_layout,parent,false)
        return ViewHolder(view)
    }

}