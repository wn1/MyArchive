package com.qdev.myarchive.ui.mvvm.pattern

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.qdev.myarchive.R

class _PatternKotlinActivity : AppCompatActivity(),
        _PatternJavaViewModel._PatternJavaMessagesView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun showMessage(message: String?) {
        TODO("implement")
    }
}