package com.palkesh.todoList.dashboard

import com.palkesh.todoList.BasePresenter
import com.palkesh.todoList.BaseView
import com.palkesh.todoList.data.dashboard.model.responseModels.TaskResponseModel

interface DashboardContract {
    interface View: BaseView<DashboardContract.Presenter>{
        fun onError(message: String)
    }

    interface ShowTaskView: View {
        fun onTaskAddSuccess(task: TaskResponseModel)
        fun onGettingTaskList(taskList: ArrayList<TaskResponseModel>)
        fun onTaskDeleteSuccess(taskList: ArrayList<TaskResponseModel>)
    }

    interface Presenter: BasePresenter

    interface ShowTaskPresenter: Presenter {
        fun getTaskList()
        fun addTask(taskDescription: String)
        fun deleteTask(task: TaskResponseModel)
    }
}