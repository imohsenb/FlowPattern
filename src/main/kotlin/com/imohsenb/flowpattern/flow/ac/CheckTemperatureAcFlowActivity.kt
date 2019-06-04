package com.imohsenb.flowpattern.flow.ac

import com.imohsenb.flowpattern.flow.FlowActivity

class CheckTemperatureAcFlowActivity : FlowActivity<AcFlowContext> {
    override suspend fun run(context: AcFlowContext): FlowActivity<AcFlowContext>? {
        context.apply {
            return when {
                currentTemp > highTemp -> CoolingAcFlowActivity()
                currentTemp < lowTemp -> HeatingAcFlowActivity()
                else -> TurnOffDeviceAcFlowActivity()
            }
        }
    }
}