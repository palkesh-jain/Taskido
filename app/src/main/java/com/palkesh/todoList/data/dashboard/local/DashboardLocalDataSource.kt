package com.palkesh.todoList.data.dashboard.local

import com.palkesh.todoList.data.DataSourceCallBack
import com.palkesh.todoList.data.dashboard.DashboardDataSource
import com.palkesh.todoList.data.dashboard.model.responseModels.TaskResponseModel
import com.palkesh.todoList.utils.Constants
import com.palkesh.todoList.utils.GsonUtils
import com.palkesh.todoList.utils.PreferenceManager

object DashboardLocalDataSource: DashboardDataSource {

    override fun getTasksList(callback: DataSourceCallBack<ArrayList<TaskResponseModel>>) {
        val taskString = PreferenceManager.getString(Constants.PREF_TASKS)
        val tasksList = GsonUtils.getTaskListFromPrefString(taskString)
        callback.onSuccess(tasksList)
    }

    override fun addTask(task: TaskResponseModel, callback: DataSourceCallBack<ArrayList<TaskResponseModel>>) {
        var taskString = PreferenceManager.getString(Constants.PREF_TASKS)
        val taskList = GsonUtils.getTaskListFromPrefString(taskString)
        taskList.add(task)
        taskString = GsonUtils.getPrefStringFromTaskList(taskList)
        PreferenceManager.putString(Constants.PREF_TASKS, taskString)
        callback.onSuccess(taskList)
    }

    override fun deleteTask(task: TaskResponseModel, callback: DataSourceCallBack<ArrayList<TaskResponseModel>>) {
        var taskString = PreferenceManager.getString(Constants.PREF_TASKS)
        val tasksList = GsonUtils.getTaskListFromPrefString(taskString)
        tasksList.remove(task)
        taskString = GsonUtils.getPrefStringFromTaskList(tasksList)
        PreferenceManager.putString(Constants.PREF_TASKS,taskString)
        callback.onSuccess(tasksList)
    }
}