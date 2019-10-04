package com.example.timernav.ui.log

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.timernav.R
import com.example.timernav.databinding.ItemLogDateBinding

class DateAdapter(val context: Context, dateList: ArrayList<String>,
                  private val listener: OnDateClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data: MutableList<String> = dateList.toMutableList()

    interface OnDateClickListener {
        fun onDateClick(item: String)
    }

    fun updateData(newData: List<String>) = with(data){
        clear()
        addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val itemBinding : ItemLogDateBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_log_date , parent, false)
        return DateViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = if (data.size == 0) {
        1
    } else {
        data.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val holder = viewHolder as DateViewHolder
        holder.bindView(data[position])
        holder.itemView.setOnClickListener { listener.onDateClick(data[position]) }
    }
}

class DateViewHolder(private val itemBinding: ItemLogDateBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bindView(item: String) {
        itemBinding.tvDate.text = item
    }
}