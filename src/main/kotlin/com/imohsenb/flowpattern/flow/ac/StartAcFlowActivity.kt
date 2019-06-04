package com.imohsenb.flowpattern.flow.ac

import com.imohsenb.flowpattern.flow.FlowActivity

class StartAcFlowActivity : FlowActivity<AcFlowContext> {
    override suspend fun run(context: AcFlowContext): FlowActivity<AcFlowContext>? {
        context.apply {
            println("\r\nStart Air conditioner")
            return CheckSystemAcFlowActivity()
        }
    }
}