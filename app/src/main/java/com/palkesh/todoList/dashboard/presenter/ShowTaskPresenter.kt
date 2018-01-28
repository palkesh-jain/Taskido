package com.palkesh.todoList.dashboard.presenter

import com.palkesh.todoList.dashboard.DashboardContract
import com.palkesh.todoList.data.DataSourceCallBack
import com.palkesh.todoList.data.dashboard.DashboardDataRepository
import com.palkesh.todoList.data.dashboard.model.responseModels.TaskResponseModel

class ShowTaskPresenter(val mShowTaskView: DashboardContract.ShowTaskView): DashboardContract.ShowTaskPresenter {

    private val mDashboardRepository: DashboardDataRepository = DashboardDataRepository

    override fun getTaskList() {
        mDashboardRepository.getTasksList(object : DataSourceCallBack<ArrayList<TaskResponseModel>>{
            override fun onSuccess(data: ArrayList<TaskResponseModel>) {
                mShowTaskView.onGettingTaskList(data)
            }

            override fun onError(message: String) {
                mShowTaskView.onError(message)
            }
        })
    }

    override fun addTask(taskDescription: String) {
        val task = TaskResponseModel(taskDescription)
        mDashboardRepository.addTask(task, object : DataSourceCallBack<ArrayList<TaskResponseModel>>{
            override fun onSuccess(data: ArrayList<TaskResponseModel>) {
                mShowTaskView.onTaskAddSuccess(task)
            }

            override fun onError(message: String) {
            }
        })
    }

    override fun deleteTask(task: TaskResponseModel) {
        mDashboardRepository.deleteTask(task, object : DataSourceCallBack<ArrayList<TaskResponseModel>> {
            override fun onSuccess(data: ArrayList<TaskResponseModel>) {
                mShowTaskView.onTaskDeleteSuccess(data)
            }

            override fun onError(message: String) {
            }
        })
    }

}