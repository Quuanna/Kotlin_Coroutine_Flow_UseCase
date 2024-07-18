package com.anna.usecase_coroutine_and_test.constant

enum class DataSource(val successMsg: String, val errorMsg: String) {
    DATABASE("loading Data form DataBase", "Database empty!"),
    NETWORK("loading Data form Network", "Something went wrong!")
}
