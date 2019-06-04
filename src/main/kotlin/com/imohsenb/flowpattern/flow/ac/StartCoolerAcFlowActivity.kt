package com.imohsenb.flowpattern.flow.ac

import com.imohsenb.flowpattern.flow.FlowActivity

class StartCoolerAcFlowActivity : FlowActivity<AcFlowContext> {
    override suspend fun run(context: AcFlowContext): FlowActivity<AcFlowContext>? {
        context.apply {
            println("COOLER:: start")
            cooler = true
            heater = false
            return CheckTemperatureAcFlowActivity()
        }
    }
}