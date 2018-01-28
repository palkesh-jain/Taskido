package com.palkesh.todoList

interface BaseView<T> {
    fun setPresenter(presenter: BasePresenter)
}