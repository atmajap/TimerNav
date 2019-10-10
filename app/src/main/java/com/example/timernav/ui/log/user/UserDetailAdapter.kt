package com.example.timernav.ui.log.user

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.timernav.R
import com.example.timernav.databinding.ItemLogUserDetailBinding
import com.example.timernav.model.LogDetail

class UserDetailAdapter(val context: Context, logList: ArrayList<LogDetail>,
                  private val listener: OnUserClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data: MutableList<LogDetail> = logList.toMutableList()

    interface OnUserClickListener {
        fun onUserClick(item: LogDetail)
    }

    fun updateData(newData: List<LogDetail>) = with(data){
        clear()
        addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val itemBinding : ItemLogUserDetailBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_log_user_detail , parent, false)
        return UserDetailViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val holder = viewHolder as UserDetailViewHolder
        holder.bindView(data[position])
        holder.itemView.setOnClickListener { listener.onUserClick(data[position]) }
    }
}

class UserDetailViewHolder(private val itemBinding: ItemLogUserDetailBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bindView(item: LogDetail) {
        itemBinding.tvDate.text = item.date
        val dateNumber = "# ${item.sequence}"
        itemBinding.tvDateNumber.text = dateNumber
    }
}