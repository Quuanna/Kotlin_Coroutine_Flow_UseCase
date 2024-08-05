package com.anna.usecase_coroutine_and_test.usecase.flow.usecase4

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anna.usecase_coroutine_and_test.databinding.ActivityFlowUseCaseListBinding
import com.anna.usecase_coroutine_and_test.usecase.flow.BaseListAdapter
import com.anna.usecase_coroutine_and_test.usecase.flow.UiSateForFlow
import com.anna.usecase_coroutine_and_test.utils.gone
import com.anna.usecase_coroutine_and_test.utils.toast
import com.anna.usecase_coroutine_and_test.utils.visible
import kotlinx.coroutines.launch

/**
 *  In the `Activity`, the `repeadOnLifecycle` suspend function is used to collect emissions of the `StateFlow` in a lifecycle-aware manner.
 *  use operate `stateIn` Converts a  cold `Flow` into a hot `StateFlow` that is started in the given coroutine scope
 */
class FlowUseCase4Activity : AppCompatActivity() {

    private val binding by lazy { ActivityFlowUseCaseListBinding.inflate(layoutInflater) }
    private val viewModel: FlowUseCase4ViewModel by viewModels {
        FlowUseCase4ViewModel.factory
    }
    private var myListAdapter: BaseListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getPokemonList.collect { uiState ->
                    render(uiState)
                }
            }
        }
    }

    private fun initView() = with(binding) {
        myListAdapter = BaseListAdapter(arrayListOf())
        recyclerView.layoutManager = LinearLayoutManager(this@FlowUseCase4Activity)
        recyclerView.adapter = myListAdapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this@FlowUseCase4Activity,
                RecyclerView.VISIBLE
            )
        )
    }

    private fun render(uiState: UiSateForFlow) {
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