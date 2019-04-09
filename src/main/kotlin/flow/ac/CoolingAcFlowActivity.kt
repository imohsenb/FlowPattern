package com.imohsenb.flowpattern.flow

import com.imohsenb.flowpattern.flow.ac.AcFlowContext
import io.reactivex.Single

class CoolingAcFlowActivity : FlowActivity<AcFlowContext> {
    override fun run(context: AcFlowContext): Single<FlowActivity<*>> {
        return Single.create { flow ->
            context.apply {
                if(!cooler) {
                    flow.onSuccess(StartCoolerAcFlowActivity())
                } else {
                    currentTemp--
                    println("COOLER:: -1 = $currentTemp")
                    flow.onSuccess(CheckTemperatureAcFlowActivity())
                }
            }
        }
    }
}