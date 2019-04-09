package com.imohsenb.flowpattern.flow

import com.imohsenb.flowpattern.flow.ac.AcFlowContext
import io.reactivex.Single
import java.lang.Exception

class HeatingAcFlowActivity : FlowActivity<AcFlowContext> {
    override fun run(context: AcFlowContext): Single<FlowActivity<*>> {
        return Single.create { flow ->
            context.apply {
                if(!heater) {
                    flow.onSuccess(StartHeaterAcFlowActivity())
                } else {
                    currentTemp++
                    println("HEATER:: +1 = $currentTemp")
                    flow.onSuccess(CheckTemperatureAcFlowActivity())
                }
            }
        }
    }
}