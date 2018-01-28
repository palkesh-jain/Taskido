package com.palkesh.todoList.data.dashboard

import com.palkesh.todoList.data.DataSourceCallBack
import com.palkesh.todoList.data.dashboard.model.responseModels.TaskResponseModel

interface DashboardDataSource {
    fun getTasksList(callback: DataSourceCallBack<ArrayList<TaskResponseModel>>)
    fun addTask(task: TaskResponseModel, callback: DataSourceCallBack<ArrayList<TaskResponseModel>>)
    fun deleteTask(task: TaskResponseModel, callback: DataSourceCallBack<ArrayList<TaskResponseModel>>)
}