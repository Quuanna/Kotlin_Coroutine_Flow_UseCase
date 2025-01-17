package com.anna.usecase_coroutine_and_test.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.anna.usecase_coroutine_and_test.constant.CoroutineUseCaseType
import com.anna.usecase_coroutine_and_test.constant.FlowUseCaseType
import com.anna.usecase_coroutine_and_test.constant.UseCaseCategory
import com.anna.usecase_coroutine_and_test.databinding.ItemListViewBinding


class UseCastCategoryAdapter(
    private var useCaseCategory: UseCaseCategory,
    private val useCaseClick: (targetActivity: Class<out AppCompatActivity>?) -> Unit
) : RecyclerView.Adapter<UseCastCategoryAdapter.MyViewHolder>() {

    override fun getItemCount(): Int = useCaseCategory.useCases.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemListViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bind()

    inner class MyViewHolder(private val binding: ItemListViewBinding) :
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