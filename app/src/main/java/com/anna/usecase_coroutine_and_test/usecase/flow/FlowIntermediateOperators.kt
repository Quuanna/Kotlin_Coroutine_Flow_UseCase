package com.anna.usecase_coroutine_and_test.usecase.flow


import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.withIndex
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

/**
 * flow 因為是 cold stream，所以 intermediate operators 不會使flow啟動，需要等到最終的operators
 * 常見 Intermediate operators: withIndex、onEach、map、take、filter、onStart
 */
fun main() {

    /**
     * (1). withIndex
     * 為每個 elements 加上 index
     *
     * (2). onEach
     * 可以作為一個靈活處理各種事件，每次觸發，且不影響Flow，ex: 印log、更新flag等
     */
    runBlocking {
        val flow = flowOf("a", "b", "c")
        flow.withIndex()
            .onEach { indexedValue ->
                println("Index: ${indexedValue.index}, Value: ${indexedValue.value}")
            }.collect() // 最終 operators
    }

    println("==========================")

    /**
     * map
     * 可以靈活轉換數據型態，並創建新的Flow，替換掉原本的值
     */
    runBlocking {
        val flow = flowOf("1", "2", "3")
        flow.map {
            it.toInt()
        }.collect { value ->
            println(value) // 輸出：1、2、3
        }
    }

    println("==========================")

    /**
     * take
     * 限制Flow emit的數量，從Flow運行後開始收集指定數量的值，
     */
    runBlocking {
        val infiniteFlow = flow {
            var i = 0
            while (true) {
                emit(i++)
                delay(100) // 模拟数据发射延迟
            }
        }
        infiniteFlow.take(5).collect { value ->
            println(value) // 輸出：0, 1, 2, 3, 4
        }
    }

    println("==========================")

    /**
     * filter
     * 用於篩選Flow中的值
     */
    runBlocking {
        val numbers = flowOf(1, 2, 3, 4, 5, 6)
        val evenNumbers = numbers.filter { it % 2 == 0 }
        evenNumbers.collect { value ->
            println(value) // 輸出：2, 4, 6
        }
    }

    println("==========================")

    /**
     * onStart
     * 初始操作，flow開始後只操作一次
     */
    runBlocking {
        val flow = flowOf(1, 2, 3)
        flow.onStart {
            println("Flow starting...")
        }.collect { value ->
            println(value) // 输出：Flow starting..., 1, 2, 3
        }
    }

    println("==========================")

    /**
     * distinctUntilChanged
     * 可以過濾重複的值
     */
    runBlocking {
        val numbers = flowOf(1, 1, 2, 2, 3, 3, 3, 4)
        val distinctNumbers = numbers.distinctUntilChanged()
        distinctNumbers.collect { value ->
            println(value) // 输出：1, 2, 3, 4
        }
    }

    println("==========================")

    /**
     * zip
     * 重新組合成新的Flow，在所有输入Flow發出值後，在依序取值組合成像Pair的值，所以基數沒有對上時就部會被組合。
     */
    runBlocking {
        val numbers = flowOf(1,2,3)
        val letters = flowOf("a", "b", "c", "d")

        val zippedFlow = numbers.zip(letters) { number, letter ->
            "$number$letter" // 組合
        }

        zippedFlow.collect { value ->
            println(value) // 輸出:1a, 2b, 3c
        }
    }

    println("==========================")

    /**
     * combine
     * 重新組合新的Flow，當Flow發送後才會取值做重新組合
     */
    runBlocking {
        val numbers = flowOf(1, 2, 3, 4)
        val letters = flowOf("a", "b", "c")

        val combinedFlow = combine(numbers, letters) { number, letter ->
            "$number$letter" // 組合
        }

        combinedFlow.collect { value ->
            println(value)  // 輸出： 1a, 2a, 2b, 3b, 3c, 4c
        }
    }
}
