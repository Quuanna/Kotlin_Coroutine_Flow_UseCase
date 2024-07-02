package com.example.usecase_coroutine_and_test.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.usecase_coroutine_and_test.constant.CoroutineUseCaseType
import com.example.usecase_coroutine_and_test.constant.FlowUseCaseType
import com.example.usecase_coroutine_and_test.constant.UseCaseCategory
import com.example.usecase_coroutine_and_test.databinding.ItemGridViewBinding


class UseCastCategoryAdapter(
    private var useCaseCategory: UseCaseCategory,
    private val useCaseClick: (targetActivity: Class<out AppCompatActivity>?) -> Unit
) : RecyclerView.Adapter<UseCastCategoryAdapter.MyViewHolder>() {

    override fun getItemCount(): Int = useCaseCategory.useCases.size

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
            val coroutineUseCaseType = useCaseCategory.useCases[adapterPosition] as? CoroutineUseCaseType
            val flowUseCaseType = useCaseCategory.useCases[adapterPosition] as? FlowUseCaseType
            binding.tvTitle.text = coroutineUseCaseType?.descriptor ?: flowUseCaseType?.descriptor
            itemView.setOnClickListener {
                useCaseClick.invoke(coroutineUseCaseType?.targetActivity ?: flowUseCaseType?.targetActivity)
            }
        }
    }
}