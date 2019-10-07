package com.example.timernav.ui.log.date

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.timernav.R
import com.example.timernav.databinding.ItemLogDateDetailBinding
import com.example.timernav.model.LogDetail

class DateDetailAdapter(val context: Context, logList: ArrayList<LogDetail>,
                        private val listener: OnDateClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data: MutableList<LogDetail> = logList.toMutableList()

    interface OnDateClickListener {
        fun onDateClick(item: LogDetail)
    }

    fun updateData(newData: List<LogDetail>) = with(data){
        clear()
        addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val itemBinding : ItemLogDateDetailBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_log_date_detail , parent, false)
        return DateDetailViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val holder = viewHolder as DateDetailViewHolder
        holder.bindView(data[position])
        holder.itemView.setOnClickListener { listener.onDateClick(data[position]) }
    }
}

class DateDetailViewHolder(private val itemBinding: ItemLogDateDetailBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bindView(item: LogDetail) {
        itemBinding.tvUser.text = item.userId.toString()
        val userNumber = "# ${item.sequence}"
        itemBinding.tvUserNumber.text = userNumber
    }
}