package com.imohsenb.flowpattern.flow.ac

import flow.FlowContext

class AcFlowContext : FlowContext {
    var currentTemp = 36

    var cooler = false

    var heater = false
    var isReady = false
    var errorExits = false

    var highTemp = 32
    var lowTemp = 24
}