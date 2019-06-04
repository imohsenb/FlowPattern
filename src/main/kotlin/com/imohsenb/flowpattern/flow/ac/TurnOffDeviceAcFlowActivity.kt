package com.imohsenb.flowpattern.flow.ac

import com.imohsenb.flowpattern.flow.FlowActivity

class TurnOffDeviceAcFlowActivity : FlowActivity<AcFlowContext> {
    override suspend fun run(context: AcFlowContext): FlowActivity<AcFlowContext>? {
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
        return null
    }
}