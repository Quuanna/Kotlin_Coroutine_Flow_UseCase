package com.anna.usecase_coroutine_and_test.usecase.flow.usecase2

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anna.usecase_coroutine_and_test.databinding.ActivityFlowUseCaseListBinding
import com.anna.usecase_coroutine_and_test.usecase.flow.BaseListAdapter
import com.anna.usecase_coroutine_and_test.usecase.flow.UiSateForFlow
import com.anna.usecase_coroutine_and_test.utils.gone
import com.anna.usecase_coroutine_and_test.utils.toast
import com.anna.usecase_coroutine_and_test.utils.visible

/**
 * Basic Flow Intermediate Operators
 * use real api get pokemon data
 */

class FlowUseCase2Activity : AppCompatActivity() {

    private val binding by lazy { ActivityFlowUseCaseListBinding.inflate(layoutInflater) }
    private val viewModel: FlowUseCase2ViewModel by viewModels { FlowUseCase2ViewModel.factory }
    private var myListAdapter: BaseListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
        setupObserve()
    }

    private fun setupObserve() {
        viewModel.getPokemonList.observe(this) { uiState ->
            setupViewData(uiState)
        }
    }

    private fun initView() = with(binding) {
        myListAdapter = BaseListAdapter(arrayListOf())
        recyclerView.layoutManager = LinearLayoutManager(this@FlowUseCase2Activity)
        recyclerView.adapter = myListAdapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this@FlowUseCase2Activity,
                RecyclerView.VISIBLE
            )
        )
    }

    private fun setupViewData(uiState: UiSateForFlow) {
        when (uiState) {
            is UiSateForFlow.Loading -> binding.progressBar.visible()
            is UiSateForFlow.Success -> {
                myListAdapter?.updateList(uiState.pokemonInfo)
                binding.progressBar.gone()
            }
            is UiSateForFlow.Error -> {
                binding.progressBar.gone()
                toast(uiState.message)
            }
        }
    }

}