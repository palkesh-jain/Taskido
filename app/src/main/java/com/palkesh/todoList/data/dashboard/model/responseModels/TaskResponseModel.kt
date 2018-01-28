package com.palkesh.todoList.data.dashboard.model.responseModels

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.palkesh.todoList.utils.Constants


data class TaskResponseModel(@SerializedName("task_id") var id: Long,
                             @SerializedName("task_description") var taskDescription: String,
                             @SerializedName("task_status") var status: String): Parcelable {

    constructor(taskDescription: String): this(System.currentTimeMillis(), taskDescription, Constants.TaskStatus.PENDING.toString())

    constructor(parcel: Parcel): this(parcel.readLong(), parcel.readString(), parcel.readString())

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeLong(id)
        parcel.writeString(status)
        parcel.writeString(taskDescription)
    }

    companion object {

        val CREATOR: Parcelable.Creator<TaskResponseModel> = object : Parcelable.Creator<TaskResponseModel> {
            override fun createFromParcel(parcel: Parcel): TaskResponseModel {
                return TaskResponseModel(parcel)
            }

            override fun newArray(i: Int): Array<TaskResponseModel?> {
                return arrayOfNulls(i)
            }
        }
    }

}