package com.qdev.myarchive.ui.mvvm

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qdev.myarchive.R

class FileLFragment : Fragment() {

    companion object {
        fun newInstance() = FileLFragment()
    }

    private lateinit var viewModel: FileLViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.file_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FileLViewModel::class.java)
        viewModel.bind(FileLViewModel.Parameters())
    }

}