package com.imohsenb.flowpattern.flow

import com.imohsenb.flowpattern.flow.ac.AcFlowContext
import io.reactivex.Single
import java.lang.Exception

class StartingSystemAcFlowActivity : FlowActivity<AcFlowContext> {
    override fun run(context: AcFlowContext): Single<FlowActivity<*>> {
        return Single.create { flow ->
            println("SYSTEM: start...")
            context.apply {
                isReady = true
                errorExits = false
                flow.onSuccess(CheckSystemAcFlowActivity())
            }
        }
    }
}