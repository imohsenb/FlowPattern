package com.imohsenb.flowpattern

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    launch {
        AirConditioner().start(36)
        AirConditioner().start(19)
    }
    println("Run...")
}