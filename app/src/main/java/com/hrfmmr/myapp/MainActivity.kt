package com.hrfmmr.myapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.hrfmmr.myapp.presentation.viewmodel.MainViewModel
import com.hrfmmr.myapp.presentation.viewmodel.MainViewModelProtocol
import com.hrfmmr.myapp.util.obtainViewModel

class MainActivity : AppCompatActivity() {


    //region Props


    private lateinit var viewModel: MainViewModelProtocol
    //endregion


    //region Lifecycle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = obtainViewModel(MainViewModel::class.java).also {
            lifecycle.addObserver(it)
            bindInputsTo(it)
            bindOutputsFrom(it)
        }
    }

    override fun onDestroy() {
        lifecycle.removeObserver(viewModel)
        super.onDestroy()
    }
    //endregion


    //region Private


    private fun bindInputsTo(viewModel: MainViewModelProtocol) {
        // do something
    }

    private fun bindOutputsFrom(viewModel: MainViewModelProtocol) {
        // do something
    }
    //endregion
}
