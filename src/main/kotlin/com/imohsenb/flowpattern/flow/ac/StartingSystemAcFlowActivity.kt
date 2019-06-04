package com.imohsenb.flowpattern.flow.ac

import com.imohsenb.flowpattern.flow.FlowActivity

class StartingSystemAcFlowActivity : FlowActivity<AcFlowContext> {
    override suspend fun run(context: AcFlowContext): FlowActivity<AcFlowContext>? {
        println("SYSTEM: start...")
        context.apply {
            isReady = true
            errorExits = false
            return CheckSystemAcFlowActivity()
        }
    }
}