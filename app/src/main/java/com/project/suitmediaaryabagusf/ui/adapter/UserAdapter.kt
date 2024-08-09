package com.project.suitmediaaryabagusf.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.suitmediaaryabagusf.databinding.UserItemBinding
import com.project.suitmediaaryabagusf.models.User

class UserAdapter(): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(private val binding: UserItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.apply {
                val name = "${user.firstName} ${user.lastName}"
                tvName.text = name
                tvEmail.text = user.email
                Glide.with(binding.root.context).load(user.photo).into(civPhoto)
            }
        }

    }

    lateinit var listener: OnItemClickListener

    private val userList = mutableListOf<User>()

    fun addItems(newItems: List<User>) {
        userList.addAll(newItems)
        notifyDataSetChanged()
    }

    fun clearItems() {
        userList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position])
        holder.itemView.setOnClickListener {
            listener.onItemClicked(userList[position])
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(item: User)
    }

}