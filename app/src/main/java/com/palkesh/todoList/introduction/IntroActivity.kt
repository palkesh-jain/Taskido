package com.palkesh.todoList.introduction

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView

import com.palkesh.todoList.R
import com.palkesh.todoList.utils.PreferenceManager
import com.palkesh.todoList.dashboard.ShowTasksActivity
import com.palkesh.todoList.utils.Constants

class IntroActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.RECORD_AUDIO), MY_PERMISSIONS_REQUEST_RECORD_AUDIO)
            } else {
                init()
            }
        } else {
            init()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_RECORD_AUDIO -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                init()
            } else {
                finish()
            }
        }
    }

    private fun init() {
        if (PreferenceManager.getBoolean(Constants.PREF_FIRST_LAUNCH, true)) {
            setContentView(R.layout.activity_intro)

            val anim1 = AnimationUtils.loadAnimation(this, R.anim.intro_left_right_1)
            val anim2 = AnimationUtils.loadAnimation(this, R.anim.intro_left_right_2)
            val anim3 = AnimationUtils.loadAnimation(this, R.anim.intro_left_right_3)
            val textOne = findViewById<TextView>(R.id.text_1)
            val textTwo = findViewById<TextView>(R.id.text_2)
            val textThree = findViewById<TextView>(R.id.text_3)

            textOne.startAnimation(anim1)
            textTwo.startAnimation(anim2)
            textThree.startAnimation(anim3)
            findViewById<View>(R.id.button_start_up).setOnClickListener {
                PreferenceManager.putBoolean(Constants.PREF_FIRST_LAUNCH, false)
                startShowTasksActivity()
            }
        } else {
            startShowTasksActivity()
        }
    }

    private fun startShowTasksActivity() {
        startActivity(Intent(this, ShowTasksActivity::class.java))
        finish()
    }

    companion object {
        private val MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 2000
    }
}
