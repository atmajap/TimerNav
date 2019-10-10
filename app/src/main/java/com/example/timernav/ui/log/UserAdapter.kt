package com.example.timernav.ui.log

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.timernav.R
import com.example.timernav.databinding.ItemLogUserBinding

class UserAdapter(val context: Context, userList: ArrayList<String>,
                  private val listener: OnUserClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data: MutableList<String> = userList.toMutableList()

    interface OnUserClickListener {
        fun onUserClick(item: String)
    }

    fun updateData(newData: List<String>) = with(data){
        clear()
        addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val itemBinding : ItemLogUserBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_log_user , parent, false)
        return UserViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val holder = viewHolder as UserViewHolder
        holder.bindView(data[position])
        holder.itemView.setOnClickListener { listener.onUserClick(data[position]) }
    }
}

class UserViewHolder(private val itemBinding: ItemLogUserBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bindView(item: String) {
        itemBinding.userId.text = item
    }
}