package com.example.usertodolist.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.usertodolist.R
import com.example.usertodolist.databinding.CardLayoutBinding
import com.example.usertodolist.domine.UserResponse

class UserAdapter(val onClickListener:OnClickListner): ListAdapter<UserResponse,UserAdapter.UserViewHolder>(DiffCallBack) {
    class UserViewHolder(val binding: CardLayoutBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(userResponse: UserResponse){
            binding.userResponse = userResponse
            val int = when(userResponse.gender){
                "male"-> R.drawable.men1
                else -> {R.drawable.women}
            }
            binding.imageView.setImageResource(int)

            val color = when(userResponse.status){
                "active"-> R.color.teal_200
                else -> R.color.red
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                binding.cardView.setCardBackgroundColor(binding.root.context.getColor(color))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = CardLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return  UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
       val userResponses = getItem(position)

        holder.bind(userResponses)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(userResponses.id)
        }

    }

    companion object DiffCallBack: DiffUtil.ItemCallback<UserResponse>(){
        override fun areItemsTheSame(oldItem: UserResponse, newItem: UserResponse): Boolean = newItem.id == oldItem.id

        override fun areContentsTheSame(oldItem: UserResponse, newItem: UserResponse): Boolean = newItem.equals(oldItem)

    }
    class OnClickListner(val clickListner:(userResponseId:Int)->Unit){
        fun onClick (userResponseId: Int) = clickListner(userResponseId)
    }
}
