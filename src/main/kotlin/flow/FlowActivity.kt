package com.imohsenb.flowpattern.flow

import flow.FlowContext
import io.reactivex.Single

interface FlowActivity<T: FlowContext>{
    fun run(context : T) : Single<FlowActivity<*>>
}