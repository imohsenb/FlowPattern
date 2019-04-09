package com.imohsenb.flowpattern.flow

import com.imohsenb.flowpattern.flow.ac.AcFlowContext
import io.reactivex.Single
import java.lang.Exception

class CheckTemperatureAcFlowActivity : FlowActivity<AcFlowContext> {
    override fun run(context: AcFlowContext): Single<FlowActivity<*>> {
        return Single.create { flow ->
            context.apply {
                if(currentTemp > highTemp) {
                    flow.onSuccess(CoolingAcFlowActivity())
                } else if( currentTemp < lowTemp) {
                    flow.onSuccess(HeatingAcFlowActivity())
                } else {
                    flow.onSuccess(TurnOffDeviceAcFlowActivity())
                }
            }
        }
    }
}