package com.imohsenb.flowpattern

import com.imohsenb.flowpattern.flow.FlowActivity
import com.imohsenb.flowpattern.flow.ac.AcFlowContext
import com.imohsenb.flowpattern.flow.ac.StartAcFlowActivity

class AirConditioner {
    suspend fun start(startTemp: Int) {
        val context = AcFlowContext().apply { currentTemp = startTemp }
        var flow: FlowActivity<AcFlowContext>? = StartAcFlowActivity()
        while (flow != null) {
            flow = flow.run(context)
        }
    }
}