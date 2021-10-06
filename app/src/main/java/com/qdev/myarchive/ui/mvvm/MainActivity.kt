package com.qdev.myarchive.ui.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.qdev.myarchive.R
import com.qdev.myarchive.service.NewFileWorker
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var isFirstStart = true

    override fun onStart() {
        super.onStart()
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
//            .setRequiresDeviceIdle(true)
//            .setRequiresStorageNotLow(true)
            .build()

//        val  newFileWorkerRequest = OneTimeWorkRequestBuilder<NewFileWorker>()
//            .setConstraints(constraints)
//            .build()

        val newFileWorkerRequest = PeriodicWorkRequestBuilder<NewFileWorker>(
            6000, TimeUnit.MILLISECONDS, 3000, TimeUnit.MILLISECONDS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(applicationContext).enqueue(newFileWorkerRequest)

        if (isFirstStart) {
            isFirstStart = false
            supportFragmentManager.beginTransaction()
                .add(R.id.navigation, AboutFragment.newInstance())
                .addToBackStack("")
                .commit()

            supportFragmentManager.beginTransaction()
                .replace (R.id.navigation, FileLFragment.newInstance())
                .addToBackStack("")
                .commit()
        }
    }
}