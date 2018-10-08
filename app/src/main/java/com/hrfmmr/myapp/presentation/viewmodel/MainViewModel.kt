package com.hrfmmr.myapp.presentation.viewmodel

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModel
import com.hrfmmr.sdk.common.domain.SampleModel
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.BehaviorProcessor

interface MainViewModelInputs {
    fun doSomething()
}

interface MainViewModelOutputs {
    val textChanged: Flowable<String>
}

interface MainViewModelProtocol : LifecycleObserver {
    val inputs: MainViewModelInputs
    val outputs: MainViewModelOutputs
}


class MainViewModel(
        private val sampleModel: SampleModel
) : ViewModel(), MainViewModelProtocol, MainViewModelInputs, MainViewModelOutputs {
    override val inputs: MainViewModelInputs
        get() = this
    override val outputs: MainViewModelOutputs
        get() = this

    private val disposeBag = CompositeDisposable()


    //region Outpts


    override lateinit var textChanged: Flowable<String>
    private val textChangedProcessor = BehaviorProcessor.createDefault("")

    init {
        textChanged = textChangedProcessor
        disposeBag.add(
                sampleModel.outputs.modelChanged.subscribe {
                    textChangedProcessor.onNext(it.name)
                }
        )
    }
    //endregion


    //region Inputs


    override fun doSomething() {
        sampleModel.doSomething()
    }
    ///endregion


    //region Lifecycle


    override fun onCleared() {
        disposeBag.clear()
        super.onCleared()
    }

    //endregion
}
