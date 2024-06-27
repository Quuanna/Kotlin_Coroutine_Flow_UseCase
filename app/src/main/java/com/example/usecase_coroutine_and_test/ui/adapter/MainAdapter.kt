package com.example.usecase_coroutine_and_test.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.usecase_coroutine_and_test.data.CoroutineUseCaseType
import com.example.usecase_coroutine_and_test.data.FlowUseCaseType
import com.example.usecase_coroutine_and_test.databinding.ItemGridViewBinding

class MainAdapter(private var itemDataList: MutableList<Any>) :
    RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    private var itemOnClickListener: (() -> View.OnClickListener)? = null

    fun addItemOnClickListener(itemOnClickListener: (() -> View.OnClickListener)) {
        this.itemOnClickListener = itemOnClickListener
    }

    fun updateList(list: MutableList<Any>) {
        itemDataList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = itemDataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemGridViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bind()

    inner class MyViewHolder(private val binding: ItemGridViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {

            val coroutineUseCaseType = itemDataList[adapterPosition] as? CoroutineUseCaseType
            val flowUseCaseType = itemDataList[adapterPosition] as? FlowUseCaseType

            binding.tvTitle.text = when {
                coroutineUseCaseType != null -> coroutineUseCaseType.text
                flowUseCaseType != null -> flowUseCaseType.text
                else -> ""
            }

            itemView.setOnClickListener(itemOnClickListener?.invoke())
        }
    }
}