package com.palkesh.todoList.data

interface DataSourceCallBack<T> {
    fun onSuccess(data: T)
    fun onError(message: String)
}