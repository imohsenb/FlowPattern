package com.imohsenb.flowpattern

import com.imohsenb.flowpattern.flow.FlowActivity
import com.imohsenb.flowpattern.flow.StartAcFlowActivity
import com.imohsenb.flowpattern.flow.ac.AcFlowContext
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class AirConditioner {
    fun start(startTemp: Int) {
        val context = AcFlowContext().apply { currentTemp = startTemp }
        val flow = StartAcFlowActivity()
        execute(flow, context)
    }

    private fun execute(flow: FlowActivity<AcFlowContext>, context: AcFlowContext) {
        flow.run(context).subscribe(object : SingleObserver<FlowActivity<*>> {
            override fun onSuccess(f: FlowActivity<*>?) {
                execute(f as FlowActivity<AcFlowContext>, context)
            }

            override fun onSubscribe(d: Disposable?) {
            }

            override fun onError(e: Throwable?) {
                e?.printStackTrace()
            }
        })

    }
}