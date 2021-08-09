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
        val tag: String = javaClass.simpleName

        val pathToWatch: File
        get () {
            //TODO file from file system and request to file access
            return File ("./MyFiles")
        }
    }

    override fun doWork(): Result {
        try {
            Log.d(tag, "Worker start")
//            val file = applicationContext.getExternalFilesDir()
            val observer = object : FileObserver(pathToWatch) {
                override fun onEvent(event: Int, file: String?) {
                    Log.d(tag, "File " + pathToWatch.toString()
                            + file.toString() + ", event: $event")
                    Toast.makeText(applicationContext, "$file observed!",
                        Toast.LENGTH_LONG).show()
                }
            }
            observer.startWatching()
            Thread.sleep(500)
            return Result.retry();
        } catch (ex: Exception) {
            Log.d(tag, "Error ${ex.printStackTrace()}")
            return Result.failure(); //или Result.retry()
        }
        return Result.success()
    }

}