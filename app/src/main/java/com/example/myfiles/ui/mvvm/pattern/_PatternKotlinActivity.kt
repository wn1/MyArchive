package com.example.myfiles.ui.mvvm.pattern

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myfiles.R

class _PatternKotlinActivity : AppCompatActivity(),
        _PatternJavaViewModel._PatternKotlinMessagesView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun showMessage(message: String?) {
        TODO("implement")
    }
}