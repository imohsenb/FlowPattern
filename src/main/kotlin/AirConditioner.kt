package com.imohsenb.flowpattern

class AirConditioner {

    var currentTemp = 36

    private var cooler = false

    private var heater = false
    private var isReady = false
    private var errorExits = false

    private var highTemp = 32
    private var lowTemp = 24

    fun start() {
        println("Start Air conditioner")
        checkSystem()
    }

    private fun checkSystem() {
        if(isReady) {
            checkTemperature()
        }else if(errorExits){
            systemError("unable starting system")
        } else {
            startingSystem()
        }
    }

    private fun checkTemperature() {

        if(currentTemp > highTemp) {
            cooling()
        } else if( currentTemp < lowTemp) {
            heating()
        } else {
            turnOffDevices()
        }
    }

    private fun turnOffDevices() {
        if(heater) {
            println("HEATER:: stop")
            heater = false
        }
        if(cooler) {
            println("COOLER:: stop")
            cooler = false
        }

    }

    private fun heating() {
        if(!heater) {
            startHeater()
        } else {
            currentTemp++
            println("HEATER:: +1 = $currentTemp")
            checkTemperature()
        }
    }

    private fun startHeater() {
        println("HEATER:: start")
        cooler = false
        heater = true
        checkTemperature()
    }

    private fun cooling() {
        if(!cooler) {
            startCooler()
        } else {
            currentTemp--
            println("COOLER:: -1 = $currentTemp")
            checkTemperature()
        }
    }

    private fun startCooler() {
        println("COOLER:: start")
        cooler = true
        heater = false
        checkTemperature()
    }

    private fun systemError(msg: String) {
        println("SYSTEM: error = $msg")
    }

    private fun startingSystem() {
        println("SYSTEM: start...")
        isReady = true
        errorExits = false
        checkSystem()
    }
}