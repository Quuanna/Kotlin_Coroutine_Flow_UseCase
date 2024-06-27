package com.example.usecase_coroutine_and_test.ui

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usecase_coroutine_and_test.R
import com.example.usecase_coroutine_and_test.data.CoroutineUseCaseType
import com.example.usecase_coroutine_and_test.data.FlowUseCaseType
import com.example.usecase_coroutine_and_test.ui.adapter.MainAdapter
import com.example.usecase_coroutine_and_test.databinding.ActivityMainBinding
import com.example.usecase_coroutine_and_test.ui.viewModel.MainViewModel
import com.google.android.material.button.MaterialButtonToggleGroup

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var myAdapter: MainAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // viewModel
        // observable
        initView()
    }

    private fun initView() {
        myAdapter = MainAdapter(CoroutineUseCaseType.entries.toMutableList())
        binding.recyclerView.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayout.VERTICAL))
        }
        binding.toggleButton.addOnButtonCheckedListener(toggleButtonCheckedListener())
        myAdapter.addItemOnClickListener(::adapterItemOnClickListener)
    }

    private fun toggleButtonCheckedListener() =
        MaterialButtonToggleGroup.OnButtonCheckedListener { _, checkedId, isChecked ->
            when {
                checkedId == R.id.toggleButton1 && isChecked -> {
                    myAdapter.updateList(CoroutineUseCaseType.entries.toMutableList())
                }

                checkedId == R.id.toggleButton2 && isChecked -> {
                    myAdapter.updateList(FlowUseCaseType.entries.toMutableList())
                }
            }
        }

    private fun adapterItemOnClickListener() = View.OnClickListener {
        // TODO to UseCase Page
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.toggleButton.removeOnButtonCheckedListener(toggleButtonCheckedListener())
    }
}