package com.imohsenb.flowpattern.flow.ac

import com.imohsenb.flowpattern.flow.FlowActivity
import kotlinx.coroutines.delay

class CoolingAcFlowActivity : FlowActivity<AcFlowContext> {
    override suspend fun run(context: AcFlowContext): FlowActivity<AcFlowContext>? {
        context.apply {
            return if(!cooler) {
                StartCoolerAcFlowActivity()
            } else {
                currentTemp--
                println("COOLER:: -1 = $currentTemp")
                delay(100)
                CheckTemperatureAcFlowActivity()
            }
        }
    }
}