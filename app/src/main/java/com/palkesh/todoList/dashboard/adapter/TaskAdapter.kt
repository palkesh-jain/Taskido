package com.palkesh.todoList.dashboard.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView


import com.palkesh.todoList.R
import com.palkesh.todoList.data.dashboard.model.responseModels.TaskResponseModel

import java.util.ArrayList

class TaskAdapter(private val mContext: Context, private val mTaskList: ArrayList<TaskResponseModel>) : RecyclerView.Adapter<TaskAdapter.MyViewHolder>() {

    private val mTaskDeleteListener = mContext as TaskDeleteListener
    private val mEmptyListListener = mContext as EmptyListListener
    private var lastPosition = -1

    init {
        registerAdapterDataObserver(CustomTaskAdapterDataObserver())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.row_task, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mTaskList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val task = mTaskList[position]
        holder.mTaskTextView.text = task.taskDescription
        holder.mDeleteImageView.setOnClickListener {
            mTaskDeleteListener.onTaskDelete(task,position)
            lastPosition--
        }
        setInsertAnimation(holder.itemView, position)
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val mTaskTextView = view.findViewById<TextView>(R.id.text_task)!!
        val mDeleteImageView = view.findViewById<ImageView>(R.id.image_delete)!!

    }

    private fun setInsertAnimation(viewToAnimate: View, position: Int) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_task_right_in)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    private inner class CustomTaskAdapterDataObserver internal constructor() : RecyclerView.AdapterDataObserver() {

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount)
            if (getItemCount() == 0)
                mEmptyListListener.showTextToAddTask()
            else
                mEmptyListListener.hideTextToAddTask()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            super.onItemRangeRemoved(positionStart, itemCount)
            if (getItemCount() == 0)
                mEmptyListListener.showTextToAddTask()
            else
                mEmptyListListener.hideTextToAddTask()
        }
    }

    interface EmptyListListener {
        fun showTextToAddTask()
        fun hideTextToAddTask()
    }

    interface TaskDeleteListener {
        fun onTaskDelete(task: TaskResponseModel, position: Int)
    }

}
