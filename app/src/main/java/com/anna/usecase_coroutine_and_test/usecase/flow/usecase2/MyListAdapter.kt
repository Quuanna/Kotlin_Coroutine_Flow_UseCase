package com.anna.usecase_coroutine_and_test.usecase.flow.usecase2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anna.usecase_coroutine_and_test.databinding.ItemIndexListViewBinding

class MyListAdapter(private var results: ArrayList<PokemonList>) :
    RecyclerView.Adapter<MyListAdapter.MyViewHolder>() {

    fun updateList(lists: List<PokemonList>) {
        results.clear()
        results.addAll(lists)
        notifyItemRangeChanged(0, results.size + 1)
    }

    override fun getItemCount(): Int = results.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemIndexListViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(results[position])
    }


    inner class MyViewHolder(val binding: ItemIndexListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: PokemonList) {
            binding.tvNum.text = result.id.toString()
            binding.tvText.text = result.name
        }
    }

}