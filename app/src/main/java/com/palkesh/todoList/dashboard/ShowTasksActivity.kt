package com.palkesh.todoList.dashboard

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.support.v7.widget.LinearLayoutManager
import android.support.wear.widget.WearableRecyclerView
import android.support.wearable.activity.WearableActivity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.palkesh.todoList.BasePresenter
import com.palkesh.todoList.R
import com.palkesh.todoList.dashboard.adapter.TaskAdapter
import com.palkesh.todoList.dashboard.presenter.ShowTaskPresenter
import com.palkesh.todoList.data.dashboard.model.responseModels.TaskResponseModel
import com.palkesh.todoList.utils.Logger
import java.util.*
import kotlin.collections.ArrayList

const val TAG = "ShowTaskActivity"

class ShowTasksActivity : WearableActivity(), TaskAdapter.EmptyListListener, DashboardContract.ShowTaskView,
                                                                            TaskAdapter.TaskDeleteListener {

    private var mAddTaskTextView: TextView? = null
    private var mAddTaskLL: LinearLayout? = null

    private var mPresenter: ShowTaskPresenter? = null
    private var mRecyclerView: WearableRecyclerView? = null
    private var mTaskAdapter: TaskAdapter? = null

    private var mTasksList: ArrayList<TaskResponseModel> = ArrayList()
    private var mDeletedPosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_task)

        init()
        setEventListeners()
    }

    private fun init() {

        mRecyclerView = findViewById<WearableRecyclerView>(R.id.recycler_view)
        mAddTaskTextView = findViewById(R.id.text_add_task)
        mAddTaskLL = findViewById(R.id.ll_add)

        mRecyclerView?.isEdgeItemsCenteringEnabled = true
        mRecyclerView?.layoutManager = LinearLayoutManager(this)
        mTaskAdapter = TaskAdapter(this, mTasksList)
        mRecyclerView?.adapter = mTaskAdapter

        setPresenter(ShowTaskPresenter(this))
        mPresenter?.getTaskList()
    }

    private fun setEventListeners() {
        mAddTaskLL?.setOnClickListener {
            getTaskFromUser()
        }
    }

    private fun getTaskFromUser() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.add_task_speak_hint))
        try {
            startActivityForResult(intent, REQUEST_SPEECH_RESULT)
        } catch (a: ActivityNotFoundException) {
            Toast.makeText(applicationContext, R.string.activity_not_supported, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_SPEECH_RESULT) {
            val results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            val taskDescription = results[0]
            mPresenter?.addTask(taskDescription)
        }
    }

    override fun onGettingTaskList(taskList: ArrayList<TaskResponseModel>) {
        mTasksList.clear()
        mTasksList.addAll(taskList)
        mTaskAdapter?.notifyDataSetChanged()
        if (taskList.size > 0)
            hideTextToAddTask()
        else
            showTextToAddTask()
        Logger.d(TAG, "OnGettingTaskList: $taskList")
    }

    override fun onTaskAddSuccess(task: TaskResponseModel) {
        mTasksList.add(task)
        mTaskAdapter?.notifyItemInserted(mTaskAdapter?.itemCount!!)
        mRecyclerView?.scrollToPosition(mTaskAdapter?.itemCount!!-1)

        Logger.d(TAG, "OnTaskAddedSuccess: Updated taskList -> $mTasksList")
    }

    override fun onTaskDelete(task: TaskResponseModel, position: Int) {
        mTasksList.remove(task)
        mDeletedPosition = position
        mPresenter?.deleteTask(task)
        Logger.d(TAG, "OnTaskDelete: deleted task -> $task")
    }

    override fun onTaskDeleteSuccess(taskList: ArrayList<TaskResponseModel>) {
        mTaskAdapter?.notifyItemRemoved(mDeletedPosition)
        mTaskAdapter?.notifyItemRangeChanged(mDeletedPosition,mTaskAdapter?.itemCount!! - mDeletedPosition)

        Logger.d(TAG, "onTaskDeleteSuccess: TaskList after deletion -> $taskList")
    }

    override fun showTextToAddTask() {
        mAddTaskTextView?.visibility = View.VISIBLE
    }

    override fun hideTextToAddTask() {
        mAddTaskTextView?.visibility = View.GONE
    }

    override fun onError(message: String) {
    }

    override fun setPresenter(presenter: BasePresenter) {
        mPresenter = presenter as ShowTaskPresenter
    }

    companion object {
        const val REQUEST_SPEECH_RESULT = 1000
    }

}
