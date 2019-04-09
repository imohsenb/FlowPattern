package com.imohsenb.flowpattern.flow

import com.imohsenb.flowpattern.flow.ac.AcFlowContext
import io.reactivex.Single

class StartHeaterAcFlowActivity : FlowActivity<AcFlowContext> {
    override fun run(context: AcFlowContext): Single<FlowActivity<*>> {
        return Single.create { flow ->
            context.apply {
                println("HEATER:: start")
                cooler = false
                heater = true
                flow.onSuccess(CheckTemperatureAcFlowActivity())
            }
        }
    }
}