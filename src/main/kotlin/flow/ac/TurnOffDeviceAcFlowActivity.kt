package com.imohsenb.flowpattern.flow

import com.imohsenb.flowpattern.flow.ac.AcFlowContext
import io.reactivex.Single
import java.lang.Exception

class TurnOffDeviceAcFlowActivity : FlowActivity<AcFlowContext> {
    override fun run(context: AcFlowContext): Single<FlowActivity<*>> {
        return Single.create { flow ->
            context.apply {
                if(heater) {
                    println("HEATER:: stop")
                    heater = false
                }
                if(cooler) {
                    println("COOLER:: stop")
                    cooler = false
                }
            }
        }
    }
}