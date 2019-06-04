package com.imohsenb.flowpattern.flow.ac

import com.imohsenb.flowpattern.flow.FlowActivity

class CheckSystemAcFlowActivity : FlowActivity<AcFlowContext> {
    override suspend fun run(context: AcFlowContext): FlowActivity<AcFlowContext>? {
        println("SYSTEM: check...")
        context.apply {
            return when {
                isReady -> CheckTemperatureAcFlowActivity()
                errorExits -> FailedFlowActivity()
                else -> StartingSystemAcFlowActivity()
            }
        }
    }
}