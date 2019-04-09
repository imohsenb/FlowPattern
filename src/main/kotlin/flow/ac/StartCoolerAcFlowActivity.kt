package com.imohsenb.flowpattern.flow

import com.imohsenb.flowpattern.flow.ac.AcFlowContext
import io.reactivex.Single

class StartCoolerAcFlowActivity : FlowActivity<AcFlowContext> {
    override fun run(context: AcFlowContext): Single<FlowActivity<*>> {
        return Single.create { flow ->
            context.apply {
                println("COOLER:: start")
                cooler = true
                heater = false
                flow.onSuccess(CheckTemperatureAcFlowActivity())
            }
        }
    }
}