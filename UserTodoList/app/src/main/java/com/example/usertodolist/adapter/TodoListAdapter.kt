package com.example.usertodolist.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.usertodolist.R
import com.example.usertodolist.databinding.CardLayoutBinding
import com.example.usertodolist.databinding.CardTodoLayoutBinding

import com.example.usertodolist.domine.UserTodoResponse


class UserTodoAdapter: ListAdapter<UserTodoResponse, UserTodoAdapter.UserTodoViewHolder>(DiffCallBack) {

    class UserTodoViewHolder(val binding:CardTodoLayoutBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(userTodoResponse: UserTodoResponse){
            binding.userTodoResponse = userTodoResponse
            val color = when(userTodoResponse.status){
                "completed"-> R.color.teal_700
                "pending"->R.color.yello
                else->R.color.white
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                binding.cardView.setCardBackgroundColor(binding.root.context.getColor(color))
            }

        }

    }


    companion object DiffCallBack:DiffUtil.ItemCallback<UserTodoResponse>(){
        override fun areItemsTheSame(
            oldItem: UserTodoResponse,
            newItem: UserTodoResponse,
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: UserTodoResponse,
            newItem: UserTodoResponse,
        ): Boolean = oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserTodoViewHolder {
        val view = CardTodoLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return  UserTodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserTodoViewHolder, position: Int) {
        val itemTodo = getItem(position)
            holder.bind(itemTodo)
    }

}