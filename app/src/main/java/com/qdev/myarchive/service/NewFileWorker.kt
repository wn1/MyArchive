package com.qdev.myarchive.service

import android.content.Context
import android.os.FileObserver
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.io.File

// Работник, который при получении уведомления от слушателя о изменении файловой системы /MyFiles
// запускает 1 раз в час добавление в резервную копию

class NewFileWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    companion object {
        const val tag: String =  "NewFileWorker"

        val pathForObserve: File
        get () {
            //TODO file from file system and request to file access
            return File ("./MyFiles")
        }
    }

    override fun doWork(): Result {
        try {
            Log.d(tag, "Worker start (5000 ms)")
            Thread.sleep(5000)
            Log.d(tag, "Worker stop")
        } catch (ex: Exception) {
            Log.d(tag, "Error ${ex.printStackTrace()}")
            return Result.failure()
        }
//        return Result.retry()
        return Result.success()
    }

    override fun onStopped() {
        super.onStopped()
    }
}