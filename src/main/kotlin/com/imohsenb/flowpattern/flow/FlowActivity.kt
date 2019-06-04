package com.imohsenb.flowpattern.flow

import flow.FlowContext

interface FlowActivity<T : FlowContext> {
    suspend fun run(context: T): FlowActivity<T>?
}