package com.anna.usecase_coroutine_and_test.ui

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.anna.usecase_coroutine_and_test.constant.CoroutineUseCaseType
import com.anna.usecase_coroutine_and_test.constant.FlowUseCaseType
import com.anna.usecase_coroutine_and_test.constant.UseCaseCategory
import com.anna.usecase_coroutine_and_test.databinding.ActivityMainBinding

class UseCastCategoryActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var myAdapter: UseCastCategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        myAdapter = UseCastCategoryAdapter(
            UseCaseCategory(CoroutineUseCaseType.entries.toMutableList()),
            ::useCaseOnClickListener
        )
        binding.recyclerView.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(this@UseCastCategoryActivity)
            addItemDecoration(DividerItemDecoration(this@UseCastCategoryActivity, LinearLayout.VERTICAL))
        }


        myAdapter = UseCastCategoryAdapter(
            UseCaseCategory(FlowUseCaseType.entries.toMutableList()),
            ::useCaseOnClickListener
        )
        binding.recyclerView2.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(this@UseCastCategoryActivity)
            addItemDecoration(DividerItemDecoration(this@UseCastCategoryActivity, LinearLayout.VERTICAL))
        }
    }

    private fun useCaseOnClickListener(targetActivity: Class<out AppCompatActivity>?) {
        startActivity(Intent(applicationContext, targetActivity))
    }


}