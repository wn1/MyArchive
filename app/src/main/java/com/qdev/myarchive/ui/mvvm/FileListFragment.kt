package com.qdev.myarchive.ui.mvvm

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qdev.myarchive.R

class FileListFragment : Fragment() {

    companion object {
        fun newInstance() = FileListFragment()
    }

    private lateinit var viewModel: FileListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.file_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FileListViewModel::class.java)
        viewModel.bind(FileListViewModel.Parameters())
    }

}