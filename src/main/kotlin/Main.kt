package com.imohsenb.flowpattern

fun main(args : Array<String>) {
    println("Hello, world!")
    AirConditioner().apply {
        currentTemp = 36
    }.start()
    AirConditioner().apply {
        currentTemp = 19
    }.start()
}