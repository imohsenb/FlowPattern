package com.imohsenb.flowpattern.flow.ac

import com.imohsenb.flowpattern.flow.FlowActivity

class StartHeaterAcFlowActivity : FlowActivity<AcFlowContext> {
    override suspend fun run(context: AcFlowContext): FlowActivity<AcFlowContext>? {
        context.apply {
            println("HEATER:: start")
            cooler = false
            heater = true
            return CheckTemperatureAcFlowActivity()
        }
    }
}