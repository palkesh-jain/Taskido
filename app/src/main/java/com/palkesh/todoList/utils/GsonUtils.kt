package com.palkesh.todoList.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.palkesh.todoList.data.dashboard.model.responseModels.TaskResponseModel
import java.lang.reflect.Type

object GsonUtils {

    fun getTaskListFromPrefString(taskString: String): ArrayList<TaskResponseModel> {
        if (taskString != "") {
            val gson = Gson()
            val type: Type = object : TypeToken<ArrayList<TaskResponseModel>>() {}.type
            return gson.fromJson<ArrayList<TaskResponseModel>>(taskString,type)
        }
        return ArrayList()
    }

    fun getPrefStringFromTaskList(taskList: ArrayList<TaskResponseModel>): String {
        val gson = Gson()
        val type: Type = object : TypeToken<ArrayList<TaskResponseModel>>() {}.type
        return gson.toJson(taskList,type)
    }

}