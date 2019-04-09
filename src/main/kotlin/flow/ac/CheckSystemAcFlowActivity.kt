package com.imohsenb.flowpattern.flow

import com.imohsenb.flowpattern.flow.ac.AcFlowContext
import io.reactivex.Single
import java.lang.Exception

class CheckSystemAcFlowActivity : FlowActivity<AcFlowContext> {
    override fun run(context: AcFlowContext): Single<FlowActivity<*>> {
        return Single.create { flow ->
            println("SYSTEM: check...")
            context.apply {
                when {
                    isReady -> flow.onSuccess(CheckTemperatureAcFlowActivity())
                    errorExits -> flow.onError(Exception("unable starting system"))
                    else -> flow.onSuccess(StartingSystemAcFlowActivity())
                }
            }
        }
    }
}