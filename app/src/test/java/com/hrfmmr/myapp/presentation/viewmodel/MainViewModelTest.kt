package com.hrfmmr.myapp.presentation.viewmodel

import com.hrfmmr.sdk.common.data.entity.SampleEntity
import com.hrfmmr.sdk.common.domain.SampleModel
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MainViewModelTest {
    @Mock private lateinit var sampleModel: SampleModel


    @Before fun setUp() {
        MockitoAnnotations.initMocks(this)
        setUpStubs()
    }

    private fun setUpStubs() {
        `when`(sampleModel.inputs).thenReturn(sampleModel)
        `when`(sampleModel.outputs).thenReturn(sampleModel)
        `when`(sampleModel.outputs.modelChanged).thenReturn(Flowable.just(SampleEntity(name = "alice")))
    }

    private fun buildViewModel(): MainViewModel {
        return MainViewModel(sampleModel)
    }

    @Test fun inputs_doSomething_invokes_sampleModels_one() {
        // Given
        val viewModel = buildViewModel()

        // When
        viewModel.inputs.doSomething()

        // Then
        verify(sampleModel).doSomething()
    }

    @Test fun outputs_textChanged_emits_change_of_sampleModel_change() {
        // Given
        val viewModel = buildViewModel()
        val testSubscriber = viewModel.outputs.textChanged.test()

        // Then
        testSubscriber.assertValue("alice")
    }
}