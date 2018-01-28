package com.palkesh.todoList.data.dashboard

import com.palkesh.todoList.data.DataSourceCallBack
import com.palkesh.todoList.data.dashboard.local.DashboardLocalDataSource
import com.palkesh.todoList.data.dashboard.model.responseModels.TaskResponseModel

object DashboardDataRepository : DashboardDataSource {

    private val mDashboardLocalDataSource: DashboardLocalDataSource = DashboardLocalDataSource

    override fun getTasksList(callback: DataSourceCallBack<ArrayList<TaskResponseModel>>) {
        mDashboardLocalDataSource.getTasksList(object : DataSourceCallBack<ArrayList<TaskResponseModel>>{
            override fun onSuccess(data: ArrayList<TaskResponseModel>) {
                callback.onSuccess(data)
            }

            override fun onError(message: String) {
                callback.onError(message)
            }
        })
    }

    override fun addTask(task: TaskResponseModel, callback: DataSourceCallBack<ArrayList<TaskResponseModel>>) {
        mDashboardLocalDataSource.addTask(task, object : DataSourceCallBack<ArrayList<TaskResponseModel>>{
            override fun onSuccess(data: ArrayList<TaskResponseModel>) {
                callback.onSuccess(data)
            }

            override fun onError(message: String) {
                callback.onError(message)
            }
        })
    }

    override fun deleteTask(task: TaskResponseModel, callback: DataSourceCallBack<ArrayList<TaskResponseModel>>) {
        mDashboardLocalDataSource.deleteTask(task, object : DataSourceCallBack<ArrayList<TaskResponseModel>> {
            override fun onSuccess(data: ArrayList<TaskResponseModel>) {
                callback.onSuccess(data)
            }

            override fun onError(message: String) {
                callback.onError(message)
            }
        })
    }
}