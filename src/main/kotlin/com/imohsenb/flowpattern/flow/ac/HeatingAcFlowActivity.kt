package com.imohsenb.flowpattern.flow.ac

import com.imohsenb.flowpattern.flow.FlowActivity
import kotlinx.coroutines.delay

class HeatingAcFlowActivity : FlowActivity<AcFlowContext> {
    override suspend fun run(context: AcFlowContext): FlowActivity<AcFlowContext>? {
        context.apply {
            return if(!heater) {
                StartHeaterAcFlowActivity()
            } else {
                currentTemp++
                println("HEATER:: +1 = $currentTemp")
                delay(250)
                CheckTemperatureAcFlowActivity()
            }
        }
    }
}